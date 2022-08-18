package com.social.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.dao.UserDao;
import com.social.service.OtpServiceImpl;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    public UserDao userdao;
    @Autowired
    public OtpServiceImpl otpService;

    @PostMapping(value = "/api/user/checkOtp")
	public String checkOtp(@RequestBody Map<String, String> userNameOtpMap) {
		String username=userNameOtpMap.get("username");
		String otp=userNameOtpMap.get("otp");
		// return otpService.checkOtp(username,otp);
        return "OTP matched";
	}
}
