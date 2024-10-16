package br.com.mariaschwinn.controller;

import br.com.mariaschwinn.service.SnsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SnsController {

    @Autowired
    private SnsNotificationService notificationService;

    @PostMapping("/topicMessage")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {
        notificationService.sendNotification("Subject: Hello", message, null);
        return ResponseEntity.ok().build();
    }
}
