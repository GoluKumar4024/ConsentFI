package com.technocrats.aa.services.rules;

import com.technocrats.aa.model.ConsentRequestDetail;

public interface IProcessConsentRequest {
    Integer getExecutionSeq();
    Boolean execute(ConsentRequestDetail consentRequestDetail);
}

/**
 * 1. PrepareConsentReq
 * 2. SendConsentReq
 */
