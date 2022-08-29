package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.CommentDao;
import com.social.dao.PostDao;
import com.social.dao.UserDao;
import com.social.entity.Comment;
import com.social.entity.Post;
import com.social.entity.User;

@Service
public class CommentServiceImpl {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PostDao postDao;
    public String addComment(String email, String comment, long postId) {
        User user=userDao.findByUsername(email);
        Post post=postDao.findById(postId).orElse(null);
        if(user==null){
            return "Invalid user";
        }
        if(post==null){
            return "Invalid Post";
        }
        commentDao.save(Comment.builder().commentBy(user).commentPost(post).content(comment).build());
        return "comment added";
    }
    public List<Comment> getCommentOnAPost(Long postId) {
        Post post=postDao.findById(postId).orElse(null);
        if(post==null){
            return null;
        }
        List<Comment> comments= post.getComments();
        comments.forEach(a->
        a.setCommentBy(
            User.builder().email(a.getCommentBy().getEmail())
                            .firstName(a.getCommentBy().getFirstName())
                            .lastName(a.getCommentBy().getLastName())
                .build()));
        return comments;
    }

}
