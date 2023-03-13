package com.technocrats.aa.model;

import com.technocrats.aa.dtos.ConsentArtefact;
import com.technocrats.aa.dtos.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ConsentArtefactDetail")
public class ConsentArtefactDetail {

    @Id
    private String id;

    private String requestId;

    private String consentHandleId;

    private String consentId;

    private ConsentArtefact consentArtefact;

    private ErrorInfo errorInfo;

}
