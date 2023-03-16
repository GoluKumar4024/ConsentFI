package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private ConsentArtefact.Detail ConsentDetail;

    private String consentDetailDigitalSignature;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date consentStart;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date consentExpiry;

        private String consentMode;

        private String fetchType;

        private List<String> consentTypes;

        private List<String> fiTypes;

        @JsonProperty(value = "DataConsumer")
        private DataConsumer DataConsumer;

        @JsonProperty(value = "DataProvider")
        private DataProvider DataProvider;

        @JsonProperty(value = "Customer")
        private Customer Customer;

        @JsonProperty(value = "Accounts")
        private List<AccountData> Accounts;

        @JsonProperty(value = "Purpose")
        private Purpose Purpose;

        @JsonProperty(value = "FIDataRange")
        private FIDataRange FIDataRange;

        @JsonProperty(value = "DataLife")
        private DataLife DataLife;

        @JsonProperty(value = "Frequency")
        private Frequency Frequency;

        @JsonProperty(value = "DataFilter")
        private List<com.technocrats.aa.dtos.DataFilter> DataFilter;

    }

}
