package com.technocrats.aa.services.rules;

import com.technocrats.aa.model.ConsentArtefactDetail;

public interface IProcessGeneratedConsent {
    Integer getExecutionSeq();
    Boolean execute(ConsentArtefactDetail consentArtefactDetail);
}

/**
 * The interface for Processing generating consent
 * 1. FetchConsentArtefact
 */
