package com.algaworks.algamoney.api.mail;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String sender,
			List<String> recipients, String subject, String body) {
		
		try {			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(sender);
			helper.setTo(recipients.toArray(new String[recipients.size()]));
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException exception) {
			throw new RuntimeException("Unable to send email", exception);
		}

	}
	
}
