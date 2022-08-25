package com.social.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.FriendDao;
import com.social.dao.PostDao;
import com.social.dao.UserDao;
import com.social.entity.Friends;
import com.social.entity.Likes;
import com.social.entity.Post;
import com.social.entity.User;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private FriendDao friendDao;

    public String postContent(String email,String postContent){
        User user=userDao.findByUsername(email);
        if(user==null){
            return "Login First";
        }
        Post post =Post.builder().title(postContent).createdBy(user).build();
        if(postDao.save(post)!=null){
            return "Successfully posted";
        }
        return "Problem while posting please try again";
    }

    public List<Post> getUserPosts(String email) {
        return userDao.findByUsername(email).getPosts();
    }

    public List<User> getSuggestedFriends(String email) {
        List<User> users= userDao.getSuggestedFriends(email);
        users.forEach(user->{
            user.setPassword("");
            user.setUserComments(null);
            user.setUserLikes(null);
        });
        return users;
    }

    public String sendFriendRequest(String email, Long friendId) {
        User friend1=userDao.findByUsername(email);
        User friend2= userDao.findById(friendId).orElse(null);
        Friends friends=Friends.builder().user1(friend1).user2(friend2).build();
        if(friend1!=null && friend2!=null){
            try {
                
                friendDao.save(friends);
                return "Request Sent successfully";
            } catch (Exception e) {
                return "Already Sent";
            }
        }
        return "Failed to Sent Request";
    }

    public List<User> getFriends(String email) {
        List<Long> friendsId= this.friendDao.getFriends(email);
        List<User> friends=this.userDao.findAllById(friendsId);
        System.out.println(friends);
        friends.forEach(friend->{
            friend.setPassword(null);
            friend.setUserLikes(null);
            friend.setUserComments(null);
            friend.setPosts(null);
        });
        return friends;
    }
    public List<User> pendingFriendRequest(String email) {
        List<User> friends= this.friendDao.pendingFriendRequest(email);
        friends.forEach(friend->{
            friend.setPassword(null);
            friend.setUserLikes(null);
            friend.setUserComments(null);
            friend.setPosts(null);
        });
        return friends;
    }
    public List<Likes> getUserLikes(String email){
        User user=userDao.findByUsername(email);
        List<Likes> likes=user.getUserLikes();
        likes.forEach(a->{
            a.setLikeBy(null);
            a.setLikePost(Post.builder().id(a.getLikePost().getId()).build());
        });
        return likes;
    }
}
