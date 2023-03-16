package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.services.rules.IProcessConsentNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandleRevokedConsent implements IProcessConsentNotification {

    private final ConsentDetailRepo consentDetailRepo;

    private static final String STATUS = "REVOKED";

    @Override
    public Integer getExecutionSeq() {
        return 3;
    }

    @Override
    public Boolean execute(ConsentDetail consentDetail) {
        if (!consentDetail.getStatus().equals(STATUS))
            return true;
        ConsentDetail consentDetailManaged = consentDetailRepo.findByConsentIdAndDataManagerName(consentDetail.getConsentId(), consentDetail.getDataManager().getName());
        consentDetailManaged.setStatus(consentDetail.getStatus());
        consentDetailManaged.setLastUpdatedDate(new Date());
        consentDetailRepo.save(consentDetailManaged);
        return true;
    }
}
