package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    @Query("select user from User user where user.email=:userName")
    User findByUsername(@RequestParam("userName") String userName);

    @Query("select user from User user where user.email=:username")
    UserDetails loadUserByUsername(@RequestParam("username") String username);
    
}
