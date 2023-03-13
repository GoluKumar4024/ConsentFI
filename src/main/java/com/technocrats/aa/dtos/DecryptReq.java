package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecryptReq {

    private String base64Data;

    private String base64RemoteNonce;

    private String base64YourNonce;

    private String ourPrivateKey;

    private KeyMaterial remoteKeyMaterial;
}
