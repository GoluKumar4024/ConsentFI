package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.model.ConsentDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.services.rules.IProcessConsentNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandleRejectedConsent implements IProcessConsentNotification {
    private static final String STATUS = "REJECTED";

    private final ConsentDetailRepo consentDetailRepo;

    @Override
    public Integer getExecutionSeq() {
        return 4;
    }

    @Override
    public Boolean execute(ConsentDetail consentDetail) {
        if (!consentDetail.getStatus().equals(STATUS))
            return true;
        consentDetail.setId(UUID.randomUUID().toString());
        Date dateNow = new Date();
        consentDetail.setCreatedDate(dateNow);
        consentDetail.setLastUpdatedDate(dateNow);
        consentDetailRepo.save(consentDetail);
        return (consentDetail.getErrorInfo() != null);
    }
}
