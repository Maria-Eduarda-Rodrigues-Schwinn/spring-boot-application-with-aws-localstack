package br.com.mariaschwinn.service;

import br.com.mariaschwinn.service.sqs.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqsMessageService {

    private final MessageSenderService messageSenderService;

    @Autowired
    public SqsMessageService(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }

    public void sendMessage(String message) {
        messageSenderService.send(message);
    }
}
