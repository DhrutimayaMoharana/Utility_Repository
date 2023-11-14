package com.email.email_service.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmailRequestDTO {

	private String[] toEmail;

	private String[] ccEmail;

	private String[] bccEmail;

	private String subject;

	private String body;

	private MultipartFile attachmentFile;

	private String requestToken;

	public EmailRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] getToEmail() {
		return toEmail;
	}

	public void setToEmail(String[] toEmail) {
		this.toEmail = toEmail;
	}

	public String[] getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String[] ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String[] getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String[] bccEmail) {
		this.bccEmail = bccEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MultipartFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(MultipartFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

}
