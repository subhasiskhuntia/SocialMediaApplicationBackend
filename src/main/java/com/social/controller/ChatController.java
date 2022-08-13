package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.ChatMessage;
import com.social.service.ChatService;

@RestController
@CrossOrigin
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    // @GetMapping(value = "")
    // public String getHomePage(){
    //     return "This is home page";
    // }

    @MessageMapping(value = "/message/{recipient}")
    @SendTo(value = "/topic/return-to/{recipient}")
    public ChatMessage chat(@Payload ChatMessage message){
        System.out.println(message);
        chatService.saveMessage(message);
        // ChatMessage chatMessage=message;
        return message;
    }
    @GetMapping(value = "previousMessage/{sender}/{receiver}")
    public List<ChatMessage> getPreviousMessages(@PathVariable("sender") String sender,@PathVariable("receiver") String receiver){
        return this.chatService.previousMessage(sender,receiver);
    }
}
