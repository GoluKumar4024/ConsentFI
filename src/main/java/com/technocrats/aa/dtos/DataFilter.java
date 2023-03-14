package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFilter {

    private String type;

    private String operator;

    private String value;

}
