package com.company.ems.controller;

import com.company.ems.model.ChatMessage;
import com.company.ems.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ChatController {

    @Autowired
    private ChatMessageRepository chatRepo;

    // 1. Live WebSocket Engine: Save to DB, then Broadcast
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatRepo.save(chatMessage); // 🔥 SAVED FOREVER!
        return chatMessage; 
    }

    // 2. REST API: Fetch History when you click a name
    @GetMapping("/chat/history")
    public List<ChatMessage> getHistory(@RequestParam String user1, @RequestParam String user2) {
        if (user2.equals("General Channel")) {
            return chatRepo.findByRecipientOrderByIdAsc("General Channel");
        } else {
            return chatRepo.findPrivateHistory(user1, user2);
        }
    }
}