package com.email.email_service.service;

import com.email.email_service.dto.CustomResponse;
import com.email.email_service.dto.EmailRequestDTO;

public interface EmailConfigurationService {

	CustomResponse sendMail(EmailRequestDTO emailRequestDTO);

	void updateLimitEveryNight();

}
