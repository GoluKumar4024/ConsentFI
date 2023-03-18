package com.technocrats.aa.model;

import com.technocrats.aa.dtos.ConsentNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ConsentNotificationDetail")
public class ConsentNotificationDetail {

    @Id
    private String id;

    private ConsentNotification consentNotification;
}
