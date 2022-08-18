package com.social.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.social.dao.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserDao userdao;
    @Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;	
		com.social.entity.User user = userdao.findByUsername(username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority("user"));
			return new User(user.getEmail(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}
	
	public String save(com.social.entity.User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		try {
			
			com.social.entity.User saveUser=userdao.save(user);
			return "Account Created Successfully";
		} catch (Exception e) {
			//TODO: handle exception
			return "User Already Exist";
		}
		// if (saveUser == null) {
		// 	System.out.println("User Already Exist");
		// }
		
	}
    
}
