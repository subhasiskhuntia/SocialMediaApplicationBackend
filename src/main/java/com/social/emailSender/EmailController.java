package com.social.emailSender;

import java.util.Map;
import java.util.Random;

//Java Program to Create Rest Controller that
//Defines various API for Sending Mail
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Otp;
import com.social.service.OtpServiceImpl;


//Annotation
@RestController
//Class
public class EmailController {

	@Autowired private EmailService emailService;
	@Autowired
	private OtpServiceImpl otpService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public String
	sendMail(@RequestBody Map<String, String> usernameMap)
	{	
		
//		System.out.println(details);
		// String username=usernameMap.get("username");
		// Random rnd = new Random();
	    // int number = rnd.nextInt(999999);

	    // // this will convert any number sequence into 6 character.
	    // String otp= String.format("%06d", number);
		// EmailDetails details=new EmailDetails(username,"Your OTP is "+otp+" which is valid till next 5 minutes","Your Otp For Ecom-Application","");
		// System.out.println(details);
		// Otp otpObject=Otp.builder().username(username).otp(otp).build();
		// otpService.saveOtp(otpObject);
		// String status
		// 	= emailService.sendSimpleMail(details);

		// return status;
		return "sent";
	}

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(
		@RequestBody EmailDetails details)
	{
		String status
			= emailService.sendMailWithAttachment(details);

		return status;
	}
}

