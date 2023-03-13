package com.technocrats.aa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purpose {

    private String code;

    private String refUri;

    private String text;

    @JsonProperty(value="Category")
    private PurposeCategory Category;
}
