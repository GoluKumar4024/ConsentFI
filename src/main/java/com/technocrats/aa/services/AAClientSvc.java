package com.technocrats.aa.services;

import com.technocrats.aa.constants.AaConstants;
import com.technocrats.aa.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.technocrats.aa.constants.AaConstants.*;

@Service
@RequiredArgsConstructor
public class AAClientSvc {

    private final WebClient webClientAA;

    public String checkHeartBeat() {
        return webClientAA.get()
                .uri(AaConstants.AA_HEART_BEAT_URI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public ConsentArtefact fetchArtefact(String consentId) {
        return webClientAA.get()
                .uri(AA_CONSENT_ARTEFACT_URI + "/" + consentId)
                .retrieve()
                .bodyToMono(ConsentArtefact.class)
                .block();
    }

    public SessionDetails sendDataFetchReq(FIDataReq fiDataReq) {
        return webClientAA
                .post()
                .uri(AA_FI_DATA_FETCH_REQ_URI)
                .body(Mono.just(fiDataReq), FIDataReq.class)
                .retrieve()
                .bodyToMono(SessionDetails.class)
                .block();
    }

    public FIFetchResp fetchDataForSession(String sessionId) {
        return webClientAA.get()
                .uri(AA_FI_DATA_FETCH_FOR_SESSION_URI + "/" + sessionId)
                .retrieve()
                .bodyToMono(FIFetchResp.class)
                .block();
    }

    public ConsentResp consentReqAaCall(ConsentReq consentReq) {
        return webClientAA
                .post()
                .uri(AA_CONSENT_ARTEFACT_URI)
                .body(Mono.just(consentReq), ConsentReq.class)
                .retrieve()
                .bodyToMono(ConsentResp.class)
                .block();
    }
}
