package br.com.mariaschwinn.sqs.controller;

import br.com.mariaschwinn.sqs.service.SqsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SqsController {

    @Autowired
    private SqsMessageService sqsMessageService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {
        sqsMessageService.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
