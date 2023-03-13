package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentArtefact {

    private String ver;

    private String txnid;

    private String consentId;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createTimestamp;

    @JsonProperty(value = "ConsentUse")
    private ConsentUse ConsentUse;

    @JsonProperty(value = "ConsentDetail")
    private ConsentDetail ConsentDetail;

    private String consentDetailDigitalSignature;
}
