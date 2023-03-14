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

    private String requestAlias;

    @CreatedDate
    private Date createdDate;

    private UiConsentReq uiConsentReq;

    private ConsentReq consentReq;

    private ConsentResp consentResp;

    private ConsentHandleStatus consentHandleStatus;

    private ErrorInfo errorInfo;

}
