package com.technocrats.aa.model;

import com.technocrats.aa.dtos.ConsentReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ConsentReqTemplates")
public class ConsentReqTemplates {

    @Id
    private String id;

    private String purposeCode;

    private ConsentReq consentReqTemplate;
}
