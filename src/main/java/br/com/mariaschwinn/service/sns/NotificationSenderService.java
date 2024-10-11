package br.com.mariaschwinn.service.sns;

import com.amazonaws.services.sns.AmazonSNS;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationSenderService {

    @Value("${sns.notification.name}")
    private String notificationName;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public NotificationSenderService(AmazonSNS amazonSNS) {
        this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS);
    }

    public void send(String subject, Object message, Map<String, Object> headers) {
        this.notificationMessagingTemplate.convertAndSend(notificationName, message, headers);
    }
}
