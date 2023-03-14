package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

//{
//        "ver": "1.1.2",
//        "timestamp": "2023-02-17T07:43:58.173Z",
//        "txnid": "35855d24-2dd9-44cb-b315-b269a1e6de2e",
//        "ConsentHandle": "104658b4-81f8-40ca-9ffb-41a69a68ca87",
//        "ConsentStatus": {
//        "id": "3cdc1d86-12ab-4218-8745-9319c9923d66",
//        "status": "READY"
//        }
//}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Document(collection = "ConsentHandleResponseDetails")
public class ConsentHandleResp {
    private String ver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date timestamp;

    private String txnid;

    @JsonProperty(value = "ConsentHandle")
    private String ConsentHandle;

    @JsonProperty(value = "ConsentStatus")
    private ConsentStatus ConsentStatus;
}
