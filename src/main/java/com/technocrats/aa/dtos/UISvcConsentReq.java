package com.technocrats.aa.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UISvcConsentReq {

    private String refId;

    private DataManager accAgg;

    private AAProfile aaProfile;

    private ConsentReq consentReq;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AAProfile {
        private String id;
        private String name;
    }

}
