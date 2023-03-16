
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
public class ConsentReq {

    private String ver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date timestamp;

    private String txnid;

    @JsonProperty(value = "ConsentDetail")
    private ConsentReq.Detail ConsentDetail;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date consentStart;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date consentExpiry;

        @JsonProperty("consentMode")
        private String consentMode;

        @JsonProperty("fetchType")
        private String fetchType;

        @JsonProperty("consentTypes")
        private List<String> consentTypes;

        @JsonProperty("fiTypes")
        private List<String> fiTypes;

        @JsonProperty("DataConsumer")
        private DataConsumerFIU DataConsumer;

        @JsonProperty("Customer")
        private Customer Customer;

        @JsonProperty("Purpose")
        private Purpose Purpose;

        @JsonProperty("FIDataRange")
        private FIDataRange FIDataRange;

        @JsonProperty("DataLife")
        private DataLife DataLife;

        @JsonProperty("Frequency")
        private Frequency Frequency;

        @JsonProperty("DataFilter")
        private List<DataFilter> DataFilter;
    }


}
