package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.ChatDao;
import com.social.entity.ChatMessage;

@Service
public class ChatService {
    @Autowired
    ChatDao chatDao;

    public String saveMessage(ChatMessage message){
        ChatMessage save= chatDao.save(message);
        if(save!=null){
            return "Message Saved Successfully";
        }
        return "Unable to Save the message";
    }

    public List<ChatMessage> previousMessage(String sender,String receiver) {
        return chatDao.getPreviousMessages(sender,receiver);
    }

}
