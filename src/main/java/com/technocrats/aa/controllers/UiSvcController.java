package com.technocrats.aa.controllers;

import com.technocrats.aa.dtos.ConsentResp;
import com.technocrats.aa.dtos.UiConsentReq;
import com.technocrats.aa.services.AAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UiSvcController {

    private final AAService aaService;

    @PostMapping("/RequestConsent")
    public ConsentResp sendConsentRequest(@RequestBody UiConsentReq uiConsentReq) {
        log.info("The Consent Creation Request received: {}", uiConsentReq);
        return aaService.createConsentRequest(uiConsentReq);
        // return new ResponseEntity<>(HttpStatus.OK);
    }

}
