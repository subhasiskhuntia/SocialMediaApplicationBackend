package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.entity.Likes;

public interface LikeDao extends JpaRepository<Likes,Long> {
    
}
