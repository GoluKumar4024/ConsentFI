package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentStatusNotification {

    @JsonProperty(value = "consentId")
    public String consentId;

    @JsonProperty(value = "consentStatus")
    public String consentStatus;
}
