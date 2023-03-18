package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UISvcConsentResp {

    private String refId;

    private String requestId;

    private String consentHandleId;

    private String status;

    private ErrorInfo errorInfo;
}
