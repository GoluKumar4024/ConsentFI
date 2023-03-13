package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedDHEKeyPair {

    @JsonProperty(value = "KeyMaterial")
    private KeyMaterial KeyMaterial;

    private ErrorInfo errorInfo;

    private String privateKey;

}
