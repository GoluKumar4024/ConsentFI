package com.technocrats.aa.model;

import com.technocrats.aa.dtos.FINotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FINotificationDetail {
    @Id
    private String id;
    private FINotification fiNotification;
}
