package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_history")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String sender;
    private String recipient; // "General Channel" OR a specific person's name
    private String content;
    private String time;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}