package com.technocrats.aa.model;

import com.technocrats.aa.dtos.DataManager;
import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.dtos.KeyMaterialWithNonce;
import com.technocrats.aa.dtos.SessionDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "DataFetchRequestDetail")
public class DataFetchRequestDetail {

    @Id
    private String id;

    private String requestId;

    private String consentId;

    private DataManager accAgg;

    private KeyMaterialWithNonce localKeyMaterialWithNonce;

    private String localPrivateKey;

    private SessionDetails sessionDetails;

    private ErrorInfo errorInfo;

}
