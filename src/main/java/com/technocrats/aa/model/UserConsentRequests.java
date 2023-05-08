package com.technocrats.aa.model;

import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.dtos.Purpose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "UIUserConsentRequests")
public class UserConsentRequests {
    private String userEmailId;

    private String refId;

    private List<Request> requests = new ArrayList<>();


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String requestId;

        private String consentHandleId;

        private String status;

        private ErrorInfo errorInfo;

        private Purpose purpose;

        private Date createdDate;
    }

}