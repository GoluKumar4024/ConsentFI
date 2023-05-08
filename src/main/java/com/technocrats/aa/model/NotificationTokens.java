package com.technocrats.aa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "NotificationTokens")
public class NotificationTokens {

    @Id
    private String id;

    private String emailId;

    private List<String> tokenIds;

}
