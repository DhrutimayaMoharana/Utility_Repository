package com.email.email_service.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.email.email_service.service.EmailConfigurationService;

@Component
public class Scheduler {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmailConfigurationService emailConfigurationService;

	@Scheduled(cron = "00 00 18 * * *", zone = "UTC")
//	@Scheduled(cron = "* * * * * *", zone = "UTC")
	public void update() {

		emailConfigurationService.updateLimitEveryNight();

	}
}
