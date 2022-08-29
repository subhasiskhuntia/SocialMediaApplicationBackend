package com.social.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.entity.Comment;

public interface CommentDao extends JpaRepository<Comment,Long> {
    @Query(value = "select comment.id as commentId,comment.content as content,comment.createdAt as createdAt,comment.commentPost.createdBy.firstName as postedByUserFirstName,comment.commentPost.createdBy.lastName as postedByUserLastName ,comment.commentPost.createdBy.id as postedByUserId,comment.commentPost.id as postId from Comment comment where comment.commentBy.email=:email")
    public List<Map<String,Object>> getCommentsOfUser(@Param("email") String email);
}
