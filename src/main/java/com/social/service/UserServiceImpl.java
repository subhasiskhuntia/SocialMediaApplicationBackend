package com.social.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.CommentDao;
import com.social.dao.FriendDao;
import com.social.dao.PostDao;
import com.social.dao.UserDao;
import com.social.entity.Comment;
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

    @Autowired
    private CommentDao commentDao;
    
    public User getUser(String email) {
        User user = userDao.findByUsername(email);
        if(user==null)
            return null;
        user.setPassword(null);
        // Map<String,Object> map=new HashMap<>();
        // map.put("id", user.getId());
        // map.put("createdAt", user.getCreatedAt());
        // map.put("dob", user.getDob());
        // map.put("email", user.getEmail());
        // map.put("firstName", user.getFirstName());
        // map.put("lastName", user.getLastName());
        // map.put("phone", user.getPhone());

        // List<Object> postList=new ArrayList<>();
        // user.getPosts().forEach(post->{
        //     Map<String,Object> posts=new HashMap<>();
        //     posts.put("id", post.getId());
        //     posts.put("created_at", post.getCreatedAt());
        //     posts.put("title", post.getTitle());
        //     posts.put("total_likes", post.getTotalLikes());
        //     posts.put("email", post.getCreatedBy().getEmail());
        //     posts.put("first_name", post.getCreatedBy().getFirstName());
        //     posts.put("last_name", post.getCreatedBy().getLastName());
        //     posts.put("created_by_id", post.getCreatedBy().getId());
        //     postList.add(posts);
        // });
        // map.put("posts", postList);
        // return map;
        return user;
    }

    public String postContent(String email, String postContent) {
        User user = userDao.findByUsername(email);
        if (user == null) {
            return "Login First";
        }
        Post post = Post.builder().title(postContent).createdBy(user).build();
        if (postDao.save(post) != null) {
            return "Successfully posted";
        }
        return "Problem while posting please try again";
    }

    public List<Post> getUserPosts(String email) {
        return userDao.findByUsername(email).getPosts();
    }

    public List<User> getSuggestedFriends(String email) {
        List<User> users = userDao.getSuggestedFriends(email);
        users.forEach(user -> {
            user.setPassword("");
            user.setUserComments(null);
            user.setUserLikes(null);
            user.setPosts(null);
        });
        return users;
    }

    public String sendFriendRequest(String email, Long friendId) {
        User friend1 = userDao.findByUsername(email);
        User friend2 = userDao.findById(friendId).orElse(null);
        Friends friends = Friends.builder().user1(friend1).user2(friend2).build();
        if (friend1 != null && friend2 != null) {
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
        List<Long> friendsId = this.friendDao.getFriends(email);
        List<User> friends = this.userDao.findAllById(friendsId);
        // System.out.println(friends);
        friends.forEach(friend -> {
            friend.setPassword(null);
            friend.setUserLikes(null);
            friend.setUserComments(null);
            friend.setPosts(null);
        });
        return friends;
    }

    public List<User> pendingFriendRequest(String email) {
        List<User> friends = this.friendDao.pendingFriendRequest(email);
        friends.forEach(friend -> {
            friend.setPassword(null);
            friend.setUserLikes(null);
            friend.setUserComments(null);
            friend.setPosts(null);
        });
        return friends;
    }

    public List<Likes> getUserLikes(String email) {
        User user = userDao.findByUsername(email);
        List<Likes> likes = user.getUserLikes();
        likes.forEach(a -> {
            a.setLikeBy(null);
            a.setLikePost(Post.builder().id(a.getLikePost().getId()).title(a.getLikePost().getTitle())
                    .createdAt(a.getLikePost().getCreatedAt()).build());
        });
        return likes;
    }

    public List<Map<String,Object>> getUserComments(String email) {
        return commentDao.getCommentsOfUser(email);
       
    }

    public User loadDifferentUserById(long userId) {
        User user= userDao.findById(userId).orElse(null);
        if(user==null)
            return null;
        // Map<String,Object> map=new HashMap<>();
        // map.put("id", user.getId());
        // map.put("createdAt", user.getCreatedAt());
        // map.put("dob", user.getDob());
        // map.put("email", user.getEmail());
        // map.put("firstName", user.getFirstName());
        // map.put("lastName", user.getLastName());
        // map.put("phone", user.getPhone());

        // List<Object> postList=new ArrayList<>();
        // user.getPosts().forEach(post->{
        //     Map<String,Object> posts=new HashMap<>();
        //     posts.put("id", post.getId());
        //     posts.put("created_at", post.getCreatedAt());
        //     posts.put("title", post.getTitle());
        //     posts.put("total_likes", post.getTotalLikes());
        //     posts.put("email", post.getCreatedBy().getEmail());
        //     posts.put("first_name", post.getCreatedBy().getFirstName());
        //     posts.put("last_name", post.getCreatedBy().getLastName());
        //     posts.put("created_by_id", post.getCreatedBy().getId());
        //     postList.add(posts);
        // });
        // map.put("posts", postList);
        // return map;
        user.setPassword(null);
        return user;
    }
}
