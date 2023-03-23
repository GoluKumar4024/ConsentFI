package com.technocrats.aa.controllers;

import com.technocrats.aa.dtos.UISvcConsentReq;
import com.technocrats.aa.dtos.UISvcConsentResp;
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
    public UISvcConsentResp sendConsentRequest(@RequestBody UISvcConsentReq UISvcConsentReq) {
        log.info("The Consent Creation Request received: {}", UISvcConsentReq);
        return aaService.createConsentRequest(UISvcConsentReq);
        // return new ResponseEntity<>(HttpStatus.OK);
    }

}
