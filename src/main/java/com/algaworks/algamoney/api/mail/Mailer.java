package com.algaworks.algamoney.api.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.model.UserAuthorization;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	public void warnAboutExpiredJournalEntries(
			List<JournalEntry> expired, List<UserAuthorization> recipients) {
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("journalentries", expired);
		
		List<String> emails = recipients.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.sendEmail("sender@gmail.com", 
				emails, 
				"Lançamentos vencidos", 
				"mail/warning-journalentries-duedate.html", 
				variables);
	}
	
	public void sendEmail(String sender,
			List<String> recipients, String subject, String template,
			Map<String, Object> variables) {
		
		Context context = new Context(new Locale("pt", "BR"));
		
		variables.entrySet().forEach(
				e -> context.setVariable(e.getKey(), e.getValue()));
		
		String body = thymeleaf.process(template, context);
		
		this.sendEmail(sender, recipients, subject, body);
	}
	
	
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
