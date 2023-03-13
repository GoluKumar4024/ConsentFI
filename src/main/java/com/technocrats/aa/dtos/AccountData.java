package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountData {

    private String fiType;

    private String fipId;

    private String accType;

    private String linkRefNumber;

    private String maskedAccNumber;

}
