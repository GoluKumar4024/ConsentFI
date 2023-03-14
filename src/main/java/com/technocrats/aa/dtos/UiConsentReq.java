package com.technocrats.aa.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UiConsentReq {

    private String email;
    private String name;
    private AA aa;
    private Purpose purpose;
    private String requestAlias;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AA {
        private String id;
        private String name;
    }

}
