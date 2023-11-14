package com.email.email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.email_service.dto.CustomResponse;
import com.email.email_service.dto.EmailRequestDTO;
import com.email.email_service.service.EmailConfigurationService;

@RestController
@RequestMapping("api")
public class EmailController {

	@Autowired
	private EmailConfigurationService emailConfigurationService;

	@PostMapping("/send")
	public ResponseEntity<?> sendMail(@ModelAttribute EmailRequestDTO emailRequestDTO) {

		CustomResponse response = emailConfigurationService.sendMail(emailRequestDTO);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

}
