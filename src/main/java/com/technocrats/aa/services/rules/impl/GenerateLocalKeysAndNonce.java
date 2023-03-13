package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.*;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.services.DHEClientSvc;
import com.technocrats.aa.services.rules.ICreateSessionForConsent;
import com.technocrats.aa.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateLocalKeysAndNonce implements ICreateSessionForConsent {

    private final DHEClientSvc dheClientSvc;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public Boolean execute(DataFetchRequestDetail dataFetchRequestDetail) {
        try {
            log.info("Local KeyPair Generation START");
            GeneratedDHEKeyPair localDHEKeyPair = dheClientSvc.generateKey();
            if (localDHEKeyPair.getErrorInfo() != null) {
                String errorMessage = String.format("There was an Error in Generating DHE KeyPair: %s", localDHEKeyPair.getErrorInfo().getErrorMessage());
                log.error(errorMessage);
                dataFetchRequestDetail.setErrorInfo(new ErrorInfo("ERROR IN DHE KEYPAIR GENERATION", errorMessage));
                return false;
            }
            String localNonce = EncodeDecodeUtils.getRandomNonce(44);
            log.info("Local Nonce Generated");
            KeyMaterial localKeyMaterial = localDHEKeyPair.getKeyMaterial();
            KeyMaterialWithNonce localKeyMaterialWithNonce = new KeyMaterialWithNonce(localKeyMaterial, localNonce);
            log.info("Local KeyPair Generation END");
            // finally set the local key material + nonce + private key info in data
            dataFetchRequestDetail.setLocalPrivateKey(localDHEKeyPair.getPrivateKey());
            dataFetchRequestDetail.setLocalKeyMaterialWithNonce(localKeyMaterialWithNonce);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error occurred in creating new local Key material: %s", ex.getMessage());
            log.error(errorMessage);
            dataFetchRequestDetail.setErrorInfo(new ErrorInfo("ERROR IN DHE KEYPAIR GENERATION", errorMessage));
            return false;
        }
    }
}
