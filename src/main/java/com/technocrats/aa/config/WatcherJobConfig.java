package com.technocrats.aa.config;

import com.technocrats.aa.dtos.ConsentHandleResp;
import com.technocrats.aa.dtos.ConsentHandleStatus;
import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.model.ConsentRequestDetail;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.repo.*;
import com.technocrats.aa.services.AAClientSvc;
import com.technocrats.aa.services.NotificationSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class WatcherJobConfig {

    private final AAClientSvc aaClientSvc;
    private final ConsentRequestDetailRepo consentRequestDetailRepo;
    private final ConsentDetailRepo consentDetailRepo;
    private final DataFetchRequestDetailRepo dataFetchRequestDetailRepo;
    private final FIFetchDetailRepo fiFetchDetailRepo;
    private final NotificationSvc notificationSvc;

    @Scheduled(fixedDelay = 1000)
    public void updateConsentIdsForPendingConsentRequests() {
        List<ConsentRequestDetail> requestWithPendingDetails = consentRequestDetailRepo.findRequestWithPendingConsentApproval();
        for (ConsentRequestDetail request : requestWithPendingDetails) {
            ConsentHandleResp consentHandleResp = aaClientSvc.fetchStatusForHandle(request.getConsentResp().getConsentHandle());
            String consentId = consentHandleResp.getConsentStatus().getId();
            String consentStatus = consentHandleResp.getConsentStatus().getStatus();
            if (consentId != null && (consentStatus.equals("READY") || consentStatus.equals("ACTIVE"))) {
                // update the consent id for consent request
                ConsentRequestDetail consentRequestDetail = consentRequestDetailRepo.findById(request.getId()).get();
                consentRequestDetail.setConsentHandleStatus(new ConsentHandleStatus(consentId, consentStatus));
                consentRequestDetailRepo.save(consentRequestDetail);
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void updateRequestIdsForFetchedConsentArtefacts() {
        List<ConsentDetail> consentArtefactDetailList = consentDetailRepo.findAllArtefactsWithAbsentRequestId();
        for (ConsentDetail consentArtefactDetail : consentArtefactDetailList) {
            ConsentRequestDetail consentRequestDetail = consentRequestDetailRepo.findByConsentHandleStatusId(consentArtefactDetail.getConsentId());
            if (consentRequestDetail != null) {
                consentArtefactDetail.setRequestId(consentRequestDetail.getId());
                consentDetailRepo.save(consentArtefactDetail);
                // check for the purpose and status
                String purposeCode = consentArtefactDetail.getConsentArtefact().getConsentDetail().getPurpose().getCode();
                String status = consentArtefactDetail.getConsentArtefact().getStatus();
                notificationSvc.sendNotificationForConsentStatus(consentArtefactDetail.getRequestId(), purposeCode, status);
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void updateDataFetchRequestDetailWithRequestId() {
        List<DataFetchRequestDetail> dataFetchRequestDetailList = dataFetchRequestDetailRepo.findByConsentIdNullOrRequestIdNullOrBoth();
        for (DataFetchRequestDetail dataFetchRequestDetail : dataFetchRequestDetailList) {
            ConsentRequestDetail consentRequestDetail = consentRequestDetailRepo.findByConsentHandleStatusId(dataFetchRequestDetail.getConsentId());
            if (consentRequestDetail != null) {
                dataFetchRequestDetail.setRequestId(consentRequestDetail.getId());
                dataFetchRequestDetailRepo.save(dataFetchRequestDetail);
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void updateFIFetchDetailsWithRequestIdAndConsentId() {
        List<FIFetchDetail> fiFetchDetails = fiFetchDetailRepo.findByConsentIdOrRequestIdOrBothNull();
        for (FIFetchDetail fiFetchDetail : fiFetchDetails) {
            String sessionId = fiFetchDetail.getSessionId();
            DataFetchRequestDetail dataFetchRequestDetail = dataFetchRequestDetailRepo.findBySessionDetailsSessionId(sessionId);
            if (dataFetchRequestDetail != null && dataFetchRequestDetail.getConsentId() != null && dataFetchRequestDetail.getRequestId() != null) {
                fiFetchDetail.setRequestId(dataFetchRequestDetail.getRequestId());
                fiFetchDetail.setConsentId(dataFetchRequestDetail.getConsentId());
                fiFetchDetailRepo.save(fiFetchDetail);
                // now as the request id is set, we need to send the notification now.
                notificationSvc.sendNotificationForInvoiceFetch(fiFetchDetail.getRequestId());
            }
        }
    }

}
