package br.com.mariaschwinn.service;

import br.com.mariaschwinn.service.sns.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SnsNotificationService {

    private final NotificationSenderService notificationSenderService;

    @Autowired
    public SnsNotificationService(NotificationSenderService notificationSenderService) {
        this.notificationSenderService = notificationSenderService;
    }

    public void sendNotification(String subject, Object message, Map<String, Object> headers) {
        notificationSenderService.send(subject, message, headers);
    }
}
