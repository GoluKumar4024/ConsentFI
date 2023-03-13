package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.ConsentArtefact;
import com.technocrats.aa.model.ConsentArtefactDetail;
import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.services.AAClientSvc;
import com.technocrats.aa.services.rules.IProcessGeneratedConsent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FetchConsentArtefact implements IProcessGeneratedConsent {

    private final AAClientSvc aaClientSvc;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public Boolean execute(ConsentArtefactDetail consentArtefactDetail) {
        try {
            String consentId = consentArtefactDetail.getConsentId();
            ConsentArtefact consentArtefact = aaClientSvc.fetchArtefact(consentId);
            consentArtefactDetail.setConsentArtefact(consentArtefact);
            log.info("The Consent Artifact fetched for ConsentId: {}", consentId);
            return true;
        } catch (Exception ex) {
            ErrorInfo errorInfo = new ErrorInfo("Exception in Fetching Consent Artefact", ex.getMessage());
            consentArtefactDetail.setErrorInfo(errorInfo);
            return false;
        }
    }
}
