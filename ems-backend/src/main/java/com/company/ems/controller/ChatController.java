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

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatRepo.save(chatMessage);
        return chatMessage;
    }

    // 🔥 CRITICAL FIX: Added the 'type' parameter to route custom Spaces correctly
    @GetMapping("/chat/history")
    public List<ChatMessage> getHistory(
            @RequestParam String user1, 
            @RequestParam String user2,
            @RequestParam(required = false, defaultValue = "dm") String type) {
        
        if ("channel".equals(type) || user2.equals("General Channel") || user2.equals("Engineering")) {
            // Fetch all messages sent to this Space, regardless of who sent them
            return chatRepo.findByRecipientOrderByIdAsc(user2);
        } else {
            // Fetch private 1-on-1 messages
            return chatRepo.findPrivateHistory(user1, user2);
        }
    }
}