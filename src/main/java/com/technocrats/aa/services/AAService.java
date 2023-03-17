package com.technocrats.aa.services;

import com.technocrats.aa.dtos.*;
import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.model.ConsentRequestDetail;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.repo.ConsentRequestDetailRepo;
import com.technocrats.aa.repo.DataFetchRequestDetailRepo;
import com.technocrats.aa.repo.FIFetchDetailRepo;
import com.technocrats.aa.services.rules.ICreateSessionForConsent;
import com.technocrats.aa.services.rules.IProcessConsentNotification;
import com.technocrats.aa.services.rules.IProcessConsentRequest;
import com.technocrats.aa.services.rules.IProcessGeneratedSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AAService {

    private final FIFetchDetailRepo fIFetchDetailRepo;
    private final ConsentDetailRepo consentDetailRepo;
    private final DataFetchRequestDetailRepo dataFetchRequestDetailRepo;
    private final ConsentRequestDetailRepo consentRequestDetailRepo;

    private final List<IProcessConsentNotification> processGeneratedConsentSvc;
    private final List<ICreateSessionForConsent> generateSessionForConsentSvc;
    private final List<IProcessGeneratedSession> processGeneratedSessionSvc;
    private final List<IProcessConsentRequest> processConsentRequests;

    public void processConsentNotification(ConsentNotification consentNotification) {
        log.info("Processing Started for Consent Notification: {}", consentNotification);
        ConsentStatusNotification consentStatusNotification = consentNotification.getConsentStatusNotification();
        Notifier notifier = consentNotification.getNotifier();
        String consentId = consentStatusNotification.getConsentId();
        String status = consentStatusNotification.getConsentStatus();
        String dataManagerName = notifier.getId();
        String dataManagerType = notifier.getType();
        if (consentDetailRepo.findByConsentIdAndDataManagerNameAndStatus(consentId, dataManagerName, status) != null) {
            log.warn("The ConsentDetail for consentId: {}, DataManager: {} with status: {} is already there.", consentId, dataManagerName, status);
            return;
        }
        ConsentDetail consentDetail = new ConsentDetail();
        consentDetail.setRequestId(null);
        consentDetail.setConsentId(consentId);
        consentDetail.setDataManager(new DataManager(dataManagerType, dataManagerName));
        List<IProcessConsentNotification> sortedSvc = processGeneratedConsentSvc.stream().sorted(Comparator.comparingInt(IProcessConsentNotification::getExecutionSeq)).collect(Collectors.toList());
        for (IProcessConsentNotification processGeneratedConsent : sortedSvc) {
            Boolean result = processGeneratedConsent.execute(consentDetail);
            if (!result) break;
        }
        if (consentDetail.getErrorInfo() != null) {
            ConsentArtefact consentArtefact = consentDetail.getConsentArtefact();
            if (consentArtefact.getStatus().equals("ACTIVE") && consentArtefact.getConsentDetail().getPurpose().getCode().equals("105") && consentArtefact.getConsentUse().getCount() == 0) {
                createSession(consentDetail);
            }
        }
        log.info("Processing Completed for Consent Notification: {}", consentNotification);
    }

    public void createSession(ConsentDetail consentDetail) {
        log.info("Started Session Creation for Consent: {}", consentDetail.getConsentId());
        DataFetchRequestDetail dataFetchRequestDetail = new DataFetchRequestDetail();
        dataFetchRequestDetail.setRequestId(null);
        dataFetchRequestDetail.setConsentId(consentDetail.getConsentId());
        dataFetchRequestDetail.setAccAgg(consentDetail.getDataManager());
        List<ICreateSessionForConsent> sortedSvcs = generateSessionForConsentSvc.stream().sorted(Comparator.comparingInt(ICreateSessionForConsent::getExecutionSeq)).collect(Collectors.toList());
        for (ICreateSessionForConsent svc : sortedSvcs) {
            Boolean result = svc.execute(dataFetchRequestDetail);
            if (!result) break;
        }
        dataFetchRequestDetail.setId(UUID.randomUUID().toString());
        dataFetchRequestDetailRepo.save(dataFetchRequestDetail);
        log.info("Completed Session Creation for Consent: {}", consentDetail.getConsentId());
    }

    public void fetchFIData(FINotification fiNotification) {
        log.info("Started FI Fetch for Session: {}", fiNotification);
        String sessionId = fiNotification.getFIStatusNotification().getSessionId();
        DataManager dataManager = new DataManager(fiNotification.getNotifier().getType(), fiNotification.getNotifier().getId());
        FIFetchDetail fiFetchDetail = new FIFetchDetail();
        fiFetchDetail.setRequestId(null);
        fiFetchDetail.setConsentId(null);
        fiFetchDetail.setSessionId(sessionId);
        fiFetchDetail.setAccAgg(dataManager);
        List<IProcessGeneratedSession> sortedSvcs = processGeneratedSessionSvc.stream().sorted(Comparator.comparingInt(IProcessGeneratedSession::getExecutionSeq)).collect(Collectors.toList());
        for (IProcessGeneratedSession svc : sortedSvcs) {
            Boolean result = svc.execute(fiFetchDetail);
            if (!result) break;
        }
        fiFetchDetail.setId(UUID.randomUUID().toString());
        fIFetchDetailRepo.save(fiFetchDetail);
        log.info("Completed Data Fetch for session: {}", sessionId);
    }

    public ConsentResp createConsentRequest(UiConsentReq uiConsentReq) {
        log.info("Started Consent Creation for user: {}", uiConsentReq.getEmail());
        ConsentRequestDetail consentRequestDetail = new ConsentRequestDetail();
        consentRequestDetail.setId(UUID.randomUUID().toString());
        consentRequestDetail.setRequestAlias(uiConsentReq.getRequestAlias());
        consentRequestDetail.setCreatedDate(new Date());
        consentRequestDetail.setUiConsentReq(uiConsentReq);
        List<IProcessConsentRequest> sortedSvcs = processConsentRequests.stream().sorted(Comparator.comparingInt(IProcessConsentRequest::getExecutionSeq)).collect(Collectors.toList());
        for (IProcessConsentRequest svc : sortedSvcs) {
            Boolean result = svc.execute(consentRequestDetail);
            if (!result) break;
        }
        consentRequestDetailRepo.save(consentRequestDetail);
        log.info("Completed Consent Creation for user: {}", uiConsentReq.getEmail());
        return consentRequestDetail.getConsentResp();
    }
}
