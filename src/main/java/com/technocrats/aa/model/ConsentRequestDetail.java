package com.technocrats.aa.model;

import com.technocrats.aa.dtos.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ConsentRequestDetail")
public class ConsentRequestDetail {

    @Id
    private String id;

    private RequestSrcRef requestSrcRef;

    private DataManager accAgg;

    private UISvcConsentReq uiSvcConsentReq;

    private ConsentResp consentResp;

    private String consentHandleId;

    private ConsentHandleStatus consentHandleStatus;

    @CreatedDate
    private Date createdDate;

    private ErrorInfo errorInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestSrcRef {

        private String srcName;

        private String refId;
    }

}
