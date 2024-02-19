package com.RSstudies.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RealTimeDataController {

    private final SimpMessagingTemplate messagingTemplate;

    public RealTimeDataController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send/message")
    public void sendMessage(@Payload String message) {
        this.messagingTemplate.convertAndSend("/topic/receive", message);
    }
}
