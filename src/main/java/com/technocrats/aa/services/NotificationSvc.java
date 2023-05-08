package com.technocrats.aa.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.technocrats.aa.model.ConsentRequestDetail;
import com.technocrats.aa.model.UserConsentRequests;
import com.technocrats.aa.repo.ConsentRequestDetailRepo;
import com.technocrats.aa.repo.NotificationTokensRepo;
import com.technocrats.aa.repo.UserConsentRequestsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSvc {

    private final FirebaseMessaging firebaseMessaging;
    private final ConsentRequestDetailRepo consentRequestDetailRepo;
    private final UserConsentRequestsRepo userConsentRequestsRepo;
    private final NotificationTokensRepo notificationTokensRepo;

    public void sendNotificationForInvoiceFetch(String requestId) {
        ConsentRequestDetail detail = consentRequestDetailRepo.findByRequestId(requestId);
        if (detail != null) {
            String refId = detail.getRequestSrcRef().getRefId();
            UserConsentRequests userConsentRequests = userConsentRequestsRepo.findByRefId(refId);
            if (userConsentRequests != null) {
                List<String> tokens = notificationTokensRepo.findByEmailId(userConsentRequests.getUserEmailId()).getTokenIds();
                tokens.forEach((token) -> {
                    Notification notification = Notification
                            .builder().setTitle("Invoices are Ready !!")
                            .setBody(String.format("Hello user! Invoices are ready under the request: %s", requestId))
                            .build();
                    Message message = Message.builder()
                            .setToken(token)
                            .setNotification(notification)
                            .build();
                    try {
                        firebaseMessaging.send(message);
                    } catch (FirebaseMessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public void sendNotificationForConsentStatus(String requestId, String purposeCode, String status) {
        ConsentRequestDetail detail = consentRequestDetailRepo.findByRequestId(requestId);
        if (detail != null) {
            String refId = detail.getRequestSrcRef().getRefId();
            UserConsentRequests userConsentRequests = userConsentRequestsRepo.findByRefId(refId);
            if (userConsentRequests != null) {
                List<String> tokens = notificationTokensRepo.findByEmailId(userConsentRequests.getUserEmailId()).getTokenIds();
                tokens.forEach((token) -> {
                    Notification notification = Notification
                            .builder().setTitle("Consent Status Update!!")
                            .setBody(String.format("Consent status is updated with status: %s for request id: %s for purpose: %s", status, requestId, purposeCode))
                            .build();
                    Message message = Message.builder()
                            .setToken(token)
                            .setNotification(notification)
                            .build();
                    try {
                        firebaseMessaging.send(message);
                    } catch (FirebaseMessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

}
