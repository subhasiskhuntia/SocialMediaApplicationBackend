package com.social.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.entity.Post;

public interface PostDao  extends JpaRepository<Post,Long>{
    @Query(value = "select post.id as id, post.title as title , post.total_likes as totalLikes , post.created_at as createdAt ,post.created_by_id as createdById ,user.email,user.first_name,user.last_name from post "+ 
    "inner join ( select"+ 
       " case "+
            "when user1_id=:id and accept=1 then user2_id "+ 
            "when user2_id=:id and accept=1 then user1_id end as friend_id from friends union select :id as friend_id) as friend"+
             "   on friend.friend_id=post.created_by_id "+
    "inner join user on friend.friend_id=user.id "+ 
    "order by post.created_at desc ",nativeQuery = true)
    public List<Map<String,Object>> getFriendsPost(@Param("id") Long id);
    
}
