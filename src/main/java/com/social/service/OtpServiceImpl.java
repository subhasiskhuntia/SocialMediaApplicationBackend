package com.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Otp;

@Service
public class OtpServiceImpl {
    @Autowired
	OtpRepository otpRepo;
	
	public String saveOtp(Otp otp) {
		otpRepo.save(otp);
		return "Otp saved";
	}

	public String checkOtp(String username, String otp) {
		String successOrNot=otpRepo.checkOtp(username);
		System.out.println(successOrNot);
		if(successOrNot==null) {
			return "user not found";
		}
		if(successOrNot.equals(otp)) {
			return "OTP matched";
		}
		return "OTP not matched";
	}
}
