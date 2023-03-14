package com.technocrats.aa.constants;

import com.technocrats.aa.dtos.DataConsumer;
import com.technocrats.aa.dtos.DataFilter;

import java.util.Arrays;
import java.util.List;

public class AaConstants {

    public static final String ONEMONEY_API_VERSION = "1.1.2";
    public static final String STORE_CONSENT_MODE = "STORE";
    public static final String VIEW_CONSENT_MODE = "VIEW";
    public static final String ONETIME_FETCH_TYPE = "ONETIME";
    public static final String PERIODIC_FETCH_TYPE = "PERIODIC";
    public static final String MONITORING_PURPOSE = "104";
    public static final String ONETIME_PURPOSE = "105";

    public static final String AA_BASE_URL = "https://api-sandbox.onemoney.in/aa";
    public static final String AA_CONSENT_ARTEFACT_URI = "/Consent";
    public static final String AA_HEART_BEAT_URI = "/Heartbeat";
    public static final String AA_FI_DATA_FETCH_REQ_URI = "/FI/request";
    public static final String AA_FI_DATA_FETCH_FOR_SESSION_URI = "/FI/fetch";

    public static final String RAHASYA_BASE_URL = "http://localhost:8080/ecc/v1";
    public static final String RAHASYA_KEYGEN_ECC_URI = "/generateKey";
    public static final String RAHASYA_DECRYPT_ECC_URI = "/decrypt";

    public static final List<String> consentTypes;
    public static final List<String> fiTypes;
    public static final DataConsumer dataConsumer;
    public static final List<DataFilter> dataFilter;

    static {
        consentTypes = Arrays.asList("TRANSACTIONS", "PROFILE", "SUMMARY");
        fiTypes = Arrays.asList("GST_GSTR1");
        dataConsumer = new DataConsumer("GOL0134", "FIU");
        dataFilter = Arrays.asList(new DataFilter("TRANSACTIONAMOUNT", ">=", "20000"));
    }
}
