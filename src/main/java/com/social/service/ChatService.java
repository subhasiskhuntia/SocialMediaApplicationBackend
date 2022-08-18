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

    public List<ChatMessage> getPreviousContactedPerson(String sender) {
        return chatDao.getPreviousContactedPerson(sender);
    }
    // public void updateId(){
    //     List<ChatMessage> messages=chatDao.findAll();
    //     messages.forEach(message-> {

    //         char charArray[] = (message.getSender()+message.getReceiver()) .toCharArray();
    //         Arrays.sort(charArray);
    //         String newId=new String(charArray);
    //         message.setChatIdentifier(newId);
    //         System.out.println(newId);
    //         chatDao.save(message);
    //     }
    //     );
    // }

}
