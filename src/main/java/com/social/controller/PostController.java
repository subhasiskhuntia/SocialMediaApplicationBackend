package com.social.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.CommentServiceImpl;
import com.social.service.PostServiceImpl;
import com.social.entity.Comment;
import com.social.entity.Post;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    CommentServiceImpl commentService;

    @PostMapping(value = "api/user/postForUser")
    public List<Map<String, Object>> getPostsForUsers(@RequestBody Map<String, String> requestMap) {
        return postService.getPostsFromFriends(requestMap.get("email"));
    }

    @PostMapping(value = "api/user/likePost")
    public String likeAPost(@RequestBody Map<String, String> requestMap) {
        Long postId = Long.parseLong(requestMap.get("post"));
        String email = String.valueOf(requestMap.get("email"));
        return postService.likeAPost(postId, email);
    }

    @PostMapping(value = "api/user/removeLike")
    public String removeLike(@RequestBody Map<String, String> requestMap) {
        long id = Long.parseLong(requestMap.get("postId"));
        String email = requestMap.get("email");
        return this.postService.removeLike(id, email);
    }
    @PostMapping(value = "api/user/addComment")
    public String addComment(@RequestBody Map<String,String> requestMap){
        String email=requestMap.get("email");
        String comment=requestMap.get("comment");
        long postId=Long.parseLong(requestMap.get("postId"));
        return this.commentService.addComment(email,comment,postId);
    }
    @PostMapping(value = "api/user/commentOnAPost")
    public List<Comment> getCommentOnAPost(@RequestBody Map<String,String> requestMap){
        Long postId=Long.parseLong(requestMap.get("postId"));
        return this.commentService.getCommentOnAPost(postId);
    }
    @GetMapping(value = "api/user/post/{id}")
    public Map<String,Object> getSpecificPost(@PathVariable("id") long id){
        return this.postService.getThisPosts(id);
    }
}
