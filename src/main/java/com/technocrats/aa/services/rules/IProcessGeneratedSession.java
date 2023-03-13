package com.technocrats.aa.services.rules;

import com.technocrats.aa.model.FIFetchDetail;

public interface IProcessGeneratedSession {
    Integer getExecutionSeq();
    Boolean execute(FIFetchDetail fiFetchDetail);
}

/**
 * The interface for processing Generated session
 * 1. FetchFIData
 * 2. LoadLocalKeysAndNonceForSession
 * 3. DecryptFIData
 */