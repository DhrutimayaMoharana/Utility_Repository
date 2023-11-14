package com.email.email_service.serviceImpl;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.email.email_service.dto.EmailRequestDTO;
import com.email.email_service.service.EmailService;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendMail(EmailRequestDTO obj, JavaMailSender sender) {
		try {

			MimeMessage mimeMsg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

			helper.setTo(obj.getToEmail());
			helper.setSubject(obj.getSubject());
			helper.setText(obj.getBody(), true);

			if (obj.getCcEmail() != null && obj.getCcEmail().length > 0) {
				helper.setCc(obj.getCcEmail());
			}
			if (obj.getBccEmail() != null && obj.getBccEmail().length > 0) {
				helper.setBcc(obj.getBccEmail());
			}
			if (obj.getAttachmentFile() != null && obj.getAttachmentFile().getSize() > 0) {

				byte[] fileBytes = obj.getAttachmentFile().getBytes();
		        ByteArrayResource byteResource = new ByteArrayResource(fileBytes);
				
				helper.addAttachment(obj.getAttachmentFile().getOriginalFilename(),
						byteResource);
			}

			EmailThread sendEmail = new EmailThread(sender, mimeMsg);
			Thread parallelThread = new Thread(sendEmail);
			parallelThread.setPriority(Thread.MAX_PRIORITY);
			parallelThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataSource convertMultipartFileToDataSource(MultipartFile multipartFile) {
		try {
			ByteArrayResource byteArrayResource = new ByteArrayResource(multipartFile.getBytes());
			return (DataSource) byteArrayResource;
		} catch (IOException e) {
			// Handle the exception
			return null;
		}
	}

}
