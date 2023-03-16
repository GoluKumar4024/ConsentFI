package com.technocrats.aa.services.rules;

import com.technocrats.aa.model.ConsentDetail;

public interface IProcessConsentNotification {
    Integer getExecutionSeq();

    Boolean execute(ConsentDetail consentArtefactDetail);
}

/**
 * The interface for Processing generating consent
 * 1. FetchConsentArtefact
 */
