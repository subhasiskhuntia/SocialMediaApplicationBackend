package com.social.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Comment;
import com.social.entity.Likes;
import com.social.entity.Post;
import com.social.entity.User;
import com.social.service.OtpServiceImpl;
import com.social.service.UserServiceImpl;

@RestController 
@CrossOrigin
public class UserController {
    @Autowired
    public OtpServiceImpl otpService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/api/user/checkOtp")
	public String checkOtp(@RequestBody Map<String, String> userNameOtpMap) {
		String username=userNameOtpMap.get("username");
		String otp=userNameOtpMap.get("otp");
		// return otpService.checkOtp(username,otp);
        return "OTP matched";
	}
    @PostMapping(value = "/api/user/post")
    public String postContent(@RequestBody Map<String,String> postMap ){
        System.out.println(postMap);
        String email=postMap.get("email");
        String postContent=postMap.get("postContent");
        return this.userService.postContent(email, postContent);
    }

    @PostMapping(value = "/api/user/userPosts")
    public List<Post> getUserPosts(@RequestBody Map<String,String> userPostsRequests){
        String email=userPostsRequests.get("email");
        return this.userService.getUserPosts(email);
    }
    @PostMapping(value = "/api/user/suggestedFriends")
    public List<User> getSuggestedFriends(@RequestBody Map<String,String> requestBodyMap){
        String email=requestBodyMap.get("email");
        return this.userService.getSuggestedFriends(email);
    }
    @PostMapping(value = "/api/user/sendFriendRequest")
    public String sendFriendRequest(@RequestBody Map<String,String> requestMap){
        String email=requestMap.get("email");
        Long friendId=Long.parseLong(requestMap.get("friendId"));
        System.out.println(email+" "+friendId);
        return this.userService.sendFriendRequest(email,friendId);
    }
    @PostMapping(value = "/api/user/friends")
    public List<User> friends(@RequestBody Map<String ,String> requestMap){
        String email=requestMap.get("email");
        // System.out.println(email);
        return this.userService.getFriends(email);
    }
    @PostMapping(value = "/api/user/pendingFriendRequest")
    public List<User> pendingFriendRequest(@RequestBody Map<String,String> requestMap){
        String email=requestMap.get("email");
        // System.out.println(email);
        return this.userService.pendingFriendRequest(email);
    }
    @PostMapping(value = "/api/user/getUserLikes")
    public List<Likes> getUserLikes(@RequestBody Map<String,String> requestMap){
        String email=requestMap.get("email");
        return this.userService.getUserLikes(email);
    }
    @PostMapping(value = "/api/user/getUser")
    public User getUser(@RequestBody Map<String,String> requestMap){
        return this.userService.getUser(requestMap.get("email"));
    }
    @PostMapping(value = "/api/user/getUserComments")
    public ResponseEntity<?> getUserComments(@RequestBody Map<String,String> requestMap){
        return ResponseEntity.ok(this.userService.getUserComments(requestMap.get("email")));
    }
}
