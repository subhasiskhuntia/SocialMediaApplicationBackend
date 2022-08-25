package com.social.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.FriendServiceImpl;


@RestController
@CrossOrigin
public class FriendController {
    @Autowired
    FriendServiceImpl friendService;
    
    @PostMapping(value = "api/user/acceptFriendRequest")
    public String acceptFriendRequest(@RequestBody Map<String,String> requestMap){
        String email=requestMap.get("email");
        Long requestId=Long.parseLong(requestMap.get("friendId"));
        return this.friendService.acceptFriendRequest(email, requestId);
    }
}
