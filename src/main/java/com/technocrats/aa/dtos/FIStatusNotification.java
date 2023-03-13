package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIStatusNotification {

    public String sessionId;

    public String sessionStatus;

    @JsonProperty(value = "FIStatusResponse")
    public List<FIStatusResponse> FIStatusResponse;

}
