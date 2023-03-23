package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.*;
import com.technocrats.aa.model.DataFetchRequestDetail;
import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.repo.DataFetchRequestDetailRepo;
import com.technocrats.aa.services.DHEClientSvc;
import com.technocrats.aa.services.rules.IProcessGeneratedSession;
import com.technocrats.aa.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DecryptFIData implements IProcessGeneratedSession {

    private final DHEClientSvc dheClientSvc;
    private final DataFetchRequestDetailRepo dataFetchRequestDetailRepo;

    @Override
    public Integer getExecutionSeq() {
        return 2;
    }

    @Override
    public Boolean execute(FIFetchDetail fiFetchDetail) {
        try {
            String sessionId = fiFetchDetail.getSessionId();
            log.info("Started Decryption of FI Data for session: {}", sessionId);
            DataFetchRequestDetail dataFetchRequestDetail = dataFetchRequestDetailRepo.findBySessionDetailsSessionIdAndDataManager(sessionId, fiFetchDetail.getAccAgg().getName());
            // findBySessionDetailsSessionId(sessionId);
            KeyMaterialWithNonce localKeyMaterialWithNonce = dataFetchRequestDetail.getLocalKeyMaterialWithNonce();
            String localNonce = localKeyMaterialWithNonce.getNonce();
            String localPrivateKey = dataFetchRequestDetail.getLocalPrivateKey();
            FIFetchResp fiData = fiFetchDetail.getData();
            for (FI FI : fiData.getFI()) {
                KeyMaterialWithNonce remoteKeyMaterialWithNonce = FI.getKeyMaterial();
                List<FIData> dataList = FI.getData();
                String remoteNonce = remoteKeyMaterialWithNonce.getNonce();
                for (FIData data : dataList) {
                    String encryptedData = data.getEncryptedFI();
                    DecryptReq reqBody = new DecryptReq(encryptedData, remoteNonce, localNonce, localPrivateKey, remoteKeyMaterialWithNonce);
                    DecryptResp decryptResp = dheClientSvc.decryptData(reqBody);
                    data.setDecryptedFI(EncodeDecodeUtils.base64Decode(decryptResp.getBase64Data()));
                }
            }
            log.info("Completed Decryption of FI Data for session: {}", sessionId);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Decrypting the Fi Data: %s", ex.getMessage());
            log.error(errorMessage);
            fiFetchDetail.setErrorInfo(new ErrorInfo("ERROR_IN_DECRYPTION_OF_FI", errorMessage));
            return false;
        }
    }
}
