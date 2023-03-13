package com.technocrats.aa.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIData {

    private String linkRefNumber;

    private String maskedAccNumber;

    private String encryptedFI;

    private String decryptedFI;
}
