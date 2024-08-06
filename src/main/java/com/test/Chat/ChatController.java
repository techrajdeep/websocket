package com.test.Chat;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage/{uuid}")
    @SendTo("/topic/messages/{uuid}")
    public ChatMessage sendMessage(@DestinationVariable String uuid, ChatMessage chatMessage) {
        System.out.println(chatMessage);
        System.out.println(uuid);
         return chatMessage;
    }
}
