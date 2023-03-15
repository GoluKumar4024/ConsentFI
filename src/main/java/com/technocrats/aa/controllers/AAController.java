package com.technocrats.aa.controllers;

import com.technocrats.aa.dtos.ConsentNotification;
import com.technocrats.aa.dtos.ConsentNotificationResp;
import com.technocrats.aa.dtos.ConsentResp;
import com.technocrats.aa.dtos.FINotificationResp;
import com.technocrats.aa.dtos.FINotification;
import com.technocrats.aa.dtos.UiConsentReq;
import com.technocrats.aa.model.ConsentNotificationDetail;
import com.technocrats.aa.model.FINotificationDetail;
import com.technocrats.aa.repo.FINotificationDetailRepo;
import com.technocrats.aa.services.AAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.technocrats.aa.repo.ConsentNotificationDetailRepo;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AAController {

    private final AAService aaService;
    private final ConsentNotificationDetailRepo consentNotificationDetailRepo;
    private final FINotificationDetailRepo fiNotificationDetailRepo;

    @PostMapping("/Consent/Notification")
    public ConsentNotificationResp processConsent(@RequestBody ConsentNotification consentNotification) {
        log.info("The Consent Notification Received: {}", consentNotification);
        logConsentNotificationToDB(consentNotification);
        String consentId = consentNotification.getConsentStatusNotification().getConsentId();
        aaService.processConsent(consentId);
        ConsentNotificationResp response = new ConsentNotificationResp(consentNotification.getVer(), consentNotification.getTimestamp(), consentNotification.getTxnid(), "OK");
        return response;
    }

    @PostMapping("/FI/Notification")
    public FINotificationResp processSession(@RequestBody FINotification fiNotification) {
        log.info("The Session Notification Received: {}", fiNotification);
        logFINotificationToDB(fiNotification);
        String sessionId = fiNotification.getFIStatusNotification().getSessionId();
        aaService.fetchFIData(sessionId);
        FINotificationResp response = new FINotificationResp(fiNotification.getVer(), fiNotification.getTimestamp(), fiNotification.getTxnid(), "OK");
        return response;
    }

    public void logConsentNotificationToDB(ConsentNotification consentNotification){
        consentNotificationDetailRepo.save(new ConsentNotificationDetail(UUID.randomUUID().toString(), consentNotification));
    }

    public void logFINotificationToDB(FINotification fiNotification){
        fiNotificationDetailRepo.save(new FINotificationDetail(UUID.randomUUID().toString(), fiNotification));
    }
}
