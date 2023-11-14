package com.email.email_service.service;

import org.springframework.mail.javamail.JavaMailSender;

import com.email.email_service.dto.EmailRequestDTO;

public interface EmailService {

	void sendMail(EmailRequestDTO obj,JavaMailSender javaMailSender);

}
