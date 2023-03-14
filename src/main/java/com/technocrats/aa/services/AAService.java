package com.technocrats.aa.services;

import com.technocrats.aa.dtos.ConsentResp;
import com.technocrats.aa.dtos.UiConsentReq;
import com.technocrats.aa.model.ConsentArtefactDetail;
import com.technocrats.aa.model.ConsentRequestDetail;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.repo.ConsentArtefactDetailRepo;
import com.technocrats.aa.repo.ConsentRequestDetailRepo;
import com.technocrats.aa.repo.DataFetchRequestDetailRepo;
import com.technocrats.aa.repo.FIFetchDetailRepo;
import com.technocrats.aa.services.rules.ICreateSessionForConsent;
import com.technocrats.aa.services.rules.IProcessConsentRequest;
import com.technocrats.aa.services.rules.IProcessGeneratedConsent;
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
    private final ConsentArtefactDetailRepo consentArtefactDetailRepo;
    private final DataFetchRequestDetailRepo dataFetchRequestDetailRepo;
    private final ConsentRequestDetailRepo consentRequestDetailRepo;

    private final List<IProcessGeneratedConsent> processGeneratedConsentSvc;
    private final List<ICreateSessionForConsent> generateSessionForConsentSvc;
    private final List<IProcessGeneratedSession> processGeneratedSessionSvc;
    private final List<IProcessConsentRequest> processConsentRequests;


    public void processConsent(String consentId) {
        log.info("Processing of Consent Started for consentId: {}", consentId);

        // set requestId, consentHandle null for now.
        ConsentArtefactDetail consentArtefactDetail = new ConsentArtefactDetail();
        consentArtefactDetail.setRequestId(null);
        consentArtefactDetail.setConsentHandleId(null);
        consentArtefactDetail.setConsentId(consentId);

        // sort svcs acc to execution order and proceed.
        List<IProcessGeneratedConsent> sortedSvc = processGeneratedConsentSvc.stream().sorted(Comparator.comparingInt(IProcessGeneratedConsent::getExecutionSeq)).collect(Collectors.toList());
        for (IProcessGeneratedConsent processGeneratedConsent : sortedSvc) {
            Boolean result = processGeneratedConsent.execute(consentArtefactDetail);
            if (!result) break;
        }

        // set Unique ID and save in DB collection
        consentArtefactDetail.setId(UUID.randomUUID().toString());
        consentArtefactDetailRepo.save(consentArtefactDetail);

        // If the purpose code is onetime reporting, then create session for it as well.
        if (consentArtefactDetail.getErrorInfo() == null) {
            // String purposeCode = consentArtefactDetail.getConsentArtefact().getConsentDetail().getPurpose().getCode();
            String fetchType = consentArtefactDetail.getConsentArtefact().getConsentDetail().getFetchType();
            log.info("The Fetch Type for Consent Id: {} is {}", consentId, fetchType);
            if (fetchType.equals("ONETIME")) {     // means one time financial reporting.
                createSession(consentId);
            }
        }
        log.info("Processing of Consent Completed for consentId: {}", consentId);
    }

    public void createSession(String consentId) {
        log.info("Started Session Creation for Consent: {}", consentId);

        // set consent id and session id
        DataFetchRequestDetail dataFetchRequestDetail = new DataFetchRequestDetail();
        dataFetchRequestDetail.setConsentId(consentId);
        dataFetchRequestDetail.setRequestId(null);

        // go through the services
        List<ICreateSessionForConsent> sortedSvcs = generateSessionForConsentSvc.stream().sorted(Comparator.comparingInt(ICreateSessionForConsent::getExecutionSeq)).collect(Collectors.toList());
        for (ICreateSessionForConsent svc : sortedSvcs) {
            Boolean result = svc.execute(dataFetchRequestDetail);
            if (!result) break;
        }

        // set unique id and save in DB
        dataFetchRequestDetail.setId(UUID.randomUUID().toString());
        dataFetchRequestDetailRepo.save(dataFetchRequestDetail);
        log.info("Completed Session Creation for Consent: {}", consentId);
    }

    public void fetchFIData(String sessionId) {
        log.info("Started FI Fetch for session: {}", sessionId);

        // set requestId, consentId, and sessionId
        FIFetchDetail fiFetchDetail = new FIFetchDetail();
        fiFetchDetail.setRequestId(null);
        fiFetchDetail.setConsentId(null);
        fiFetchDetail.setSessionId(sessionId);

        // go through services
        List<IProcessGeneratedSession> sortedSvcs = processGeneratedSessionSvc.stream().sorted(Comparator.comparingInt(IProcessGeneratedSession::getExecutionSeq)).collect(Collectors.toList());
        for (IProcessGeneratedSession svc : sortedSvcs) {
            Boolean result = svc.execute(fiFetchDetail);
            if (!result) break;
        }

        // set unique id and save in DB
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
