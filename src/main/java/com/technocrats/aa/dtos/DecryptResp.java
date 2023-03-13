package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecryptResp {

    private String base64Data;

    private ErrorInfo errorInfo;

}
