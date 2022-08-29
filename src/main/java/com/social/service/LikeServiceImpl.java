package com.social.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.LikeDao;

@Service
public class LikeServiceImpl {
    @Autowired
    LikeDao likeDao;

    public List<Map<String,Object>> getUsersLikeOnPosts(String email){
        return this.likeDao.getUsersLikeOnPosts(email);
    }
}
