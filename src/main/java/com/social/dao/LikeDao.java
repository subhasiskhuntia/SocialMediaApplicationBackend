package com.social.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.entity.Likes;

public interface LikeDao extends JpaRepository<Likes,Long> {
    @Query("select likes from Likes likes where likes.likeBy.email=:email and likes.likePost.id=:postId")
    public Likes getLikedPost(@Param("email") String email,@Param("postId") long postId);

    @Query(value = "select likes.id as likeId,likes.likePost.createdBy.firstName as postedByUserFirstName,likes.likePost.createdBy.lastName as postedByUserLastName ,likes.likePost.createdBy.id as postedByUserId , likes.createdAt as likedOn , likes.likePost.id as postId from Likes likes where likes.likeBy.email=:email ")
    public List<Map<String,Object>> getUsersLikeOnPosts(@Param("email") String email);
}
