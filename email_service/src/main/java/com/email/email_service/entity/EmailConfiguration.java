package com.email.email_service.entity;

import java.util.Date;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_configuration")
public class EmailConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_name")
	private String applicationName;

	@Column(name = "expire_date")
	private Date expireDate;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "email_token")
	private String emailToken;

	@Column(name = "daily_limit")
	private Long dailyLimit;

	@Column(name = "daily_attempt")
	private Long dailyAttempt;

	@Column(name = "max_limit")
	private Long maxLimit;

	@Column(name = "no_of_time_limit_exceeds")
	private Long noOfTimeLimitExceeds;

	public EmailConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}

	public Long getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(Long dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public Long getDailyAttempt() {
		return dailyAttempt;
	}

	public void setDailyAttempt(Long dailyAttempt) {
		this.dailyAttempt = dailyAttempt;
	}

	public Long getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(Long maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Long getNoOfTimeLimitExceeds() {
		return noOfTimeLimitExceeds;
	}

	public void setNoOfTimeLimitExceeds(Long noOfTimeLimitExceeds) {
		this.noOfTimeLimitExceeds = noOfTimeLimitExceeds;
	}

}
