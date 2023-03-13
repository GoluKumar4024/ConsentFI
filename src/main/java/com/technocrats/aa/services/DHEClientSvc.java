package com.technocrats.aa.services;

import com.technocrats.aa.constants.AaConstants;
import com.technocrats.aa.dtos.DecryptReq;
import com.technocrats.aa.dtos.DecryptResp;
import com.technocrats.aa.dtos.GeneratedDHEKeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DHEClientSvc {

    private final WebClient webClientDHE;

    public GeneratedDHEKeyPair generateKey() {
        return webClientDHE.get()
                .uri(AaConstants.RAHASYA_KEYGEN_ECC_URI)
                .retrieve()
                .bodyToMono(GeneratedDHEKeyPair.class)
                .block();
    }

    public DecryptResp decryptData(DecryptReq decryptReq) {
        return webClientDHE.post()
                .uri(AaConstants.RAHASYA_DECRYPT_ECC_URI)
                .body(Mono.just(decryptReq), DecryptReq.class)
                .retrieve()
                .bodyToMono(DecryptResp.class)
                .block();
    }
}
