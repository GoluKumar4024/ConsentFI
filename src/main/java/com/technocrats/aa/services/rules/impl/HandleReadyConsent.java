package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.ConsentArtefact;
import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.services.AAClientSvc;
import com.technocrats.aa.services.rules.IProcessConsentNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static com.technocrats.aa.constants.AaConstants.ERROR_IN_ARTEFACT_FETCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandleReadyConsent implements IProcessConsentNotification {

    private static final String STATUS = "READY";

    private final AAClientSvc aaClientSvc;
    private final ConsentDetailRepo consentDetailRepo;

    @Override
    public Integer getExecutionSeq() {
        return 2;
    }

    @Override
    public Boolean execute(ConsentDetail consentDetail) {
        // if the current status is not equal to handler status, simply pass on.
        if (!consentDetail.getStatus().equals(STATUS))
            return true;
        try {
            ConsentArtefact consentArtefact = aaClientSvc.fetchArtefact(consentDetail.getConsentId());
            consentDetail.setConsentArtefact(consentArtefact);
        } catch (Exception ex) {
            log.error(ERROR_IN_ARTEFACT_FETCH, ex);
            consentDetail.setErrorInfo(new ErrorInfo(ERROR_IN_ARTEFACT_FETCH, ex.getMessage()));
        }
        consentDetail.setId(UUID.randomUUID().toString());
        Date dateNow = new Date();
        consentDetail.setCreatedDate(dateNow);
        consentDetail.setLastUpdatedDate(dateNow);
        consentDetailRepo.save(consentDetail);
        return (consentDetail.getErrorInfo() != null);
    }
}
