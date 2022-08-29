package com.social.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.LikeServiceImpl;

@RestController
@CrossOrigin
public class LikeController {
    @Autowired
    private LikeServiceImpl likeService;
    
    @PostMapping(value = "/api/user/getUsersLikeOnPosts")
    public ResponseEntity<?> getUsersLikeOnPosts(@RequestBody Map<String,String> requestMap){
        return ResponseEntity.ok(this.likeService.getUsersLikeOnPosts(requestMap.get("email")));
    }
}
