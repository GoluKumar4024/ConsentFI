package com.technocrats.aa.services.rules;

import com.technocrats.aa.model.DataFetchRequestDetail;

public interface ICreateSessionForConsent {
    Integer getExecutionSeq();
    Boolean execute(DataFetchRequestDetail dataFetchRequestDetail);
}

/**
 * 1. GenerateLocalKeysAndNonce
 * 2. SendFIDataRequest
 */
