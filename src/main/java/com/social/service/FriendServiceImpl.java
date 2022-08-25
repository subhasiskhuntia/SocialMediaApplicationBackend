package com.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.FriendDao;
import com.social.dao.UserDao;
import com.social.entity.Friends;

@Service
public class FriendServiceImpl {
    @Autowired
    FriendDao friendDao;
    @Autowired
    UserDao userDao;

    public String acceptFriendRequest(String email,long requestId){
        
        Friends friend= friendDao.getSelectedFriendRequest(requestId, email);
        friend.setAccept(true);
        if(friendDao.save(friend)!=null){
            return "Request Accepted";
        }
        return "Failed while accepting the request";
    }
}
