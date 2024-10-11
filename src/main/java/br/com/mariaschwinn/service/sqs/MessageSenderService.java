package br.com.mariaschwinn.service.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderService {

    @Value("${sqsQueueName}")
    private String queueName;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageSenderService(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void send(String message) {
        this.queueMessagingTemplate.send(
                queueName,
                MessageBuilder
                        .withPayload(message)
                        .build());
    }
}
