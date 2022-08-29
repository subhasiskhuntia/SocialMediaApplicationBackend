package com.social.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.social.dao.LikeDao;
import com.social.dao.PostDao;
import com.social.dao.UserDao;
import com.social.entity.Likes;
import com.social.entity.Post;
import com.social.entity.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class PostServiceImpl {
    @Autowired
    PostDao postDao;
    @Autowired
    UserDao userDao;
    @Autowired
    LikeDao likeDao;

    public List<Map<String, Object>> getPostsFromFriends(String email) {
        User user = userDao.findByUsername(email);
        return this.postDao.getFriendsPost(user.getId());
    }

    public String likeAPost(Long postId, String email) {
        Post post = postDao.findById(postId).orElse(null);
        User user = userDao.findByUsername(email);
        if (post == null) {
            return "no post found";
        }
        if (user == null) {
            return "user not found";
        }
        post.setTotalLikes(post.getTotalLikes() + 1);
        Likes likes = Likes.builder().likeBy(user).likePost(post).build();
        try {
            if (likeDao.save(likes) != null) {
                return "Liked this post";
            }
        } catch (Exception e) {
            return "Already Liked";
        }
        return "unable to like this post";
    }

    public String removeLike(long id, String email) {
        Likes likes=likeDao.getLikedPost(email,id);
        // System.out.println(likes);
        if(likes!=null){
            Post post= postDao.findById(likes.getLikePost().getId()).orElse(null);
            post.setTotalLikes(post.getTotalLikes()-1);
            postDao.saveAndFlush(post);
            likeDao.delete(likes);
            return "deleted successfully";
        }   
        return "unable to delete";
    }

}
