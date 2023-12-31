package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.*;
import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.services.AAClientSvc;
import com.technocrats.aa.services.rules.ICreateSessionForConsent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendFIDataRequest implements ICreateSessionForConsent {

    private final AAClientSvc aaClientSvc;
    private final ConsentDetailRepo consentDetailRepo;

    @Override
    public Integer getExecutionSeq() {
        return 2;
    }

    @Override
    public Boolean execute(DataFetchRequestDetail dataFetchRequestDetail) {
        try {
            String consentId = dataFetchRequestDetail.getConsentId();
            ConsentDetail consentDetail = consentDetailRepo.findByConsentIdAndDataManagerName(consentId, dataFetchRequestDetail.getAccAgg().getName());
            ConsentArtefact consentArtefact = consentDetail.getConsentArtefact();
            Consent consent = new Consent(consentId, consentArtefact.getConsentDetailDigitalSignature());
            FIDataRange fiDataRange = consentArtefact.getConsentDetail().getFIDataRange();
            FIDataReq fiDataReq = new FIDataReq("1.1.2", new Date(), UUID.randomUUID().toString(), fiDataRange, consent, dataFetchRequestDetail.getLocalKeyMaterialWithNonce());
            SessionDetails sessionDetails = aaClientSvc.sendDataFetchReq(fiDataReq);
            log.info("Generated Session Details For Consent : {} -> {}", consentId, sessionDetails);
            dataFetchRequestDetail.setSessionDetails(sessionDetails);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Sending the Data Fetch Request: %s", ex.getMessage());
            log.error(errorMessage);
            dataFetchRequestDetail.setErrorInfo(new ErrorInfo("ERROR_IN_SENDING_DATA_FETCH_REQUEST", errorMessage));
            return false;
        }
    }
}
