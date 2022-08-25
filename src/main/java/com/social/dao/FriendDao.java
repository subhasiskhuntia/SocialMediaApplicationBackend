package com.social.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.entity.Friends;
import com.social.entity.User;

public interface FriendDao extends JpaRepository<Friends,Long>{

    @Query(value = "select  case when friends.user1.email=:email then user2.id else user1.id end  from Friends friends where (friends.user1.email=:email or friends.user2.email=:email) and friends.accept=true ")
    List<Long> getFriends(@RequestParam("email") String email);
    @Query(value = "select friends.user1 from Friends friends where friends.user2.email=:email and friends.accept=false ")
    List<User> pendingFriendRequest(@RequestParam("email") String email);
    // @Modifying
    // @Transactional
    @Query(value = "select friends from Friends friends where friends.user1.id=:id and friends.user2.email=:email")
    public Friends getSelectedFriendRequest(@RequestParam("id") long id, @RequestParam("email") String email );
}
