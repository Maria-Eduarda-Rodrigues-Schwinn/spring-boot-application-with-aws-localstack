package br.com.mariaschwinn.sns.service;

import com.amazonaws.services.sns.AmazonSNS;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SnsNotificationService {

    @Value("${sns.notification.name}")
    private String notificationName;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SnsNotificationService(AmazonSNS amazonSNS) {
        this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS);
    }

    public void sendNotification(String subject, Object message, Map<String, Object> headers) {
        this.notificationMessagingTemplate.convertAndSend(notificationName, message, headers);
    }
}
