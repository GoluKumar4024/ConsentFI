package com.technocrats.aa.controllers;

import com.technocrats.aa.dtos.ConsentResp;
import com.technocrats.aa.dtos.UiConsentReq;
import com.technocrats.aa.services.AAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AAController {

    private final AAService aaService;

    @PostMapping("/ProcessConsent/{consentId}")
    public ResponseEntity<Void> processConsent(@PathVariable String consentId) {
        log.info("The Consent Received For Processing: {}", consentId);
        aaService.processConsent(consentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ProcessSession/{sessionId}")
    public ResponseEntity<Void> processSession(@PathVariable String sessionId) {
        log.info("The Session Received for Processing: {}", sessionId);
        aaService.fetchFIData(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/RequestConsent")
    public ConsentResp sendConsentRequest(@RequestBody UiConsentReq uiConsentReq) {
        log.info("The Consent Creation Request received: {}", uiConsentReq);
        return aaService.createConsentRequest(uiConsentReq);
        // return new ResponseEntity<>(HttpStatus.OK);
    }


}
