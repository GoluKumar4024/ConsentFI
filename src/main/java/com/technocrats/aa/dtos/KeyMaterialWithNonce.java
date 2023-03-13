package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeyMaterialWithNonce extends KeyMaterial {

    public KeyMaterialWithNonce(KeyMaterial.DHPublicKey DHPublicKey, String cryptoAlg, String curve, String params, String nonce) {
        super(DHPublicKey, cryptoAlg, curve, params);
        Nonce = nonce;
    }

    public KeyMaterialWithNonce(KeyMaterial material, String nonce) {
        this(material.getDHPublicKey(), material.getCryptoAlg(), material.getCurve(), material.getParams(), nonce);
    }

    @JsonProperty(value = "Nonce")
    private String Nonce;


}
