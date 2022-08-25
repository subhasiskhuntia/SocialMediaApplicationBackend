package com.social.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.PostServiceImpl;

import com.social.entity.Post;


@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostServiceImpl postService;
    
    @PostMapping(value = "api/user/postForUser")
    public List<Map<String,Object>> getPostsForUsers(@RequestBody Map<String,String> requestMap){
        return postService.getPostsFromFriends(requestMap.get("email"));
    }
    
    @PostMapping(value = "api/user/likePost")
    public String likeAPost(@RequestBody Map<String,String> requestMap){
        Long postId= Long.parseLong( requestMap.get("post"));
        String email=String.valueOf(requestMap.get("email"));
        return postService.likeAPost(postId,email);
    }
}
