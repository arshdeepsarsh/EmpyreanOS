package com.company.ems.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.company.ems.model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    // Grabs company-wide messages
    List<ChatMessage> findByRecipientOrderByIdAsc(String recipient);
    
    // Custom query to grab 1-on-1 messages no matter who sent them first
    @Query("SELECT c FROM ChatMessage c WHERE (c.sender = :user1 AND c.recipient = :user2) OR (c.sender = :user2 AND c.recipient = :user1) ORDER BY c.id ASC")
    List<ChatMessage> findPrivateHistory(@Param("user1") String user1, @Param("user2") String user2);
}