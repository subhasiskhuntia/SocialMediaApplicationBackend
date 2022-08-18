package com.social.emailSender;

public interface EmailService {

	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}
