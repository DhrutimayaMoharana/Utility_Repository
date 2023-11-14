package com.email.email_service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.email.email_service.dto.CustomResponse;
import com.email.email_service.dto.EmailRequestDTO;
import com.email.email_service.entity.EmailConfiguration;
import com.email.email_service.repository.EmailConfigurationRepository;
import com.email.email_service.service.EmailConfigurationService;
import com.email.email_service.service.EmailService;

import jakarta.transaction.Transactional;

@Service
public class EmailConfigurationServiceImpl implements EmailConfigurationService {

	@Autowired
	private EmailConfigurationRepository emailConfigurationRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public void updateLimitEveryNight() {
		List<EmailConfiguration> emailConfigurationList = emailConfigurationRepository.findAllActiveConfig();

		List<EmailConfiguration> emailConfigurationUpdatedList = new ArrayList<>();

		for (EmailConfiguration emailConfiguration : emailConfigurationList) {

			if (emailConfiguration.getMaxLimit() == null || emailConfiguration.getMaxLimit() == 0) {
				continue;
			}

			if ((emailConfiguration.getNoOfTimeLimitExceeds() != null
					&& emailConfiguration.getNoOfTimeLimitExceeds() >= 3)) {

				emailConfiguration.setDailyLimit(emailConfiguration.getDailyAttempt());
				emailConfiguration.setMaxLimit(emailConfiguration.getDailyAttempt());

				emailConfiguration.setNoOfTimeLimitExceeds(0L);
				emailConfiguration.setDailyAttempt(0L);
			} else {
				emailConfiguration.setDailyLimit(emailConfiguration.getMaxLimit());
				emailConfiguration.setDailyAttempt(0L);
				emailConfiguration.setNoOfTimeLimitExceeds(emailConfiguration.getNoOfTimeLimitExceeds() != null
						? emailConfiguration.getNoOfTimeLimitExceeds() + 1
						: 1L);
			}

			Date nowDate = new Date();
			
			if (emailConfiguration.getExpireDate() != null && emailConfiguration.getExpireDate().before(nowDate)) {
				emailConfiguration.setNoOfTimeLimitExceeds(0L);
				emailConfiguration.setDailyAttempt(0L);
				emailConfiguration.setMaxLimit(0L);
				emailConfiguration.setDailyLimit(0L);
			}

			emailConfigurationUpdatedList.add(emailConfiguration);

		}

		emailConfigurationRepository.saveAll(emailConfigurationUpdatedList);
	}

	@Transactional
	@Override
	public CustomResponse sendMail(EmailRequestDTO emailRequestDTO) {

		try {

			if (emailRequestDTO.getRequestToken() != null) {

				if (emailRequestDTO.getAttachmentFile() != null) {
					if (emailRequestDTO.getAttachmentFile().getSize() > 500000) {
						return new CustomResponse(HttpStatus.BAD_REQUEST.value(),
								"Attachment File size must be less than 500KB !!!!");
					}
				}

				EmailConfiguration emailConfiguration = emailConfigurationRepository
						.getByEmailToken(emailRequestDTO.getRequestToken());

				if (emailConfiguration != null) {

					if (emailConfiguration.getDailyLimit() > 0) {

						JavaMailSender javaMailSender = javaMailSender(emailConfiguration);
						if (javaMailSender != null) {

							emailService.sendMail(emailRequestDTO, javaMailSender);

							emailConfiguration.setDailyLimit(emailConfiguration.getDailyLimit() - 1);

							emailConfiguration.setDailyAttempt(emailConfiguration.getDailyAttempt() != null
									? emailConfiguration.getDailyAttempt() + 1
									: 1);

							emailConfigurationRepository.save(emailConfiguration);
							emailConfigurationRepository.flush();

							return new CustomResponse(HttpStatus.OK.value(), "Mail Send Successfully!!!!!!!!!!");

						}
					} else {

						emailConfiguration.setDailyAttempt(
								emailConfiguration.getDailyAttempt() != null ? emailConfiguration.getDailyAttempt() + 1
										: 1);

						emailConfigurationRepository.save(emailConfiguration);

						return new CustomResponse(HttpStatus.BAD_REQUEST.value(), "Limit complete for TODAY !!!!!");
					}
				}

			} else {
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), "Provide valid token !!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public JavaMailSender javaMailSender(EmailConfiguration emailConfiguration) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(emailConfiguration.getEmail());
		mailSender.setPassword(emailConfiguration.getPassword());

		Properties javaMailProperties = mailSender.getJavaMailProperties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");

		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}

}
