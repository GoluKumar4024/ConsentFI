package com.technocrats.aa.model;

import com.technocrats.aa.dtos.ConsentArtefact;
import com.technocrats.aa.dtos.DataManager;
import com.technocrats.aa.dtos.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ConsentDetail")
public class ConsentDetail {

    @Id
    private String id;

    private String requestId;

    private String consentId;

    private DataManager dataManager;

    private String status;

    private ConsentArtefact consentArtefact;

    private Date createdDate;

    private Date lastUpdatedDate;

    private ErrorInfo errorInfo;

}
