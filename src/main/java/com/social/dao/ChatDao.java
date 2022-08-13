package com.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.entity.ChatMessage;

@Repository
public interface ChatDao extends JpaRepository<ChatMessage,Long> {
    @Query(value = "select message from ChatMessage message where (message.sender=:sender and message.receiver=:receiver) or (message.sender=:receiver and message.receiver=:sender) order by message.time")
    public List<ChatMessage> getPreviousMessages(@RequestParam("sender") String sender,@RequestParam("receiver") String receiver);
}
