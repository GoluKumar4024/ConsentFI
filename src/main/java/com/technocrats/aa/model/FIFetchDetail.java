package com.technocrats.aa.model;

import com.technocrats.aa.dtos.DataManager;
import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.dtos.FIFetchResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "FIFetchDetail")
public class FIFetchDetail {

    @Id
    private String id;

    private String requestId;

    private String consentId;

    private String sessionId;

    private DataManager accAgg;

    private FIFetchResp data;

    private Date createdDate;

    private ErrorInfo errorInfo;


}
