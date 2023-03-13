package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FI {
    private String fipID;

    private List<FIData> data;

    @JsonProperty(value = "KeyMaterial")
    private KeyMaterialWithNonce KeyMaterial;
}
