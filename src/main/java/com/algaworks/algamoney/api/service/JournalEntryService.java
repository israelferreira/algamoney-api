package com.algaworks.algamoney.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.dto.JournalEntryPersonStatistics;
import com.algaworks.algamoney.api.mail.Mailer;
import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.model.UserAuthorization;
import com.algaworks.algamoney.api.repository.JournalEntryRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.repository.UserAuthorizationRepository;
import com.algaworks.algamoney.api.service.exception.NonexistentOrInactivePersonException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JournalEntryService {
	
	private static final String RECIPIENTS = "ROLE_SEARCH_JOURNALENTRY";
	
	private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserAuthorizationRepository userAuthorizationRepository;
	
	@Autowired
	private Mailer mailer;	
	
	// @Scheduled(fixedDelay = 1000 * 60 * 30) // At the start of deployment and with a 30 minutes delay loop
	@Scheduled(cron = "0 0 6 * * *") // Everyday at 6 o'clock in the morning
	public void warnAboutExpiredJournalEntries() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting ready to send "
					+ "warning e-mails about expired journal entries.");
		}
		
		List<JournalEntry> expired = journalEntryRepository
				.findByDueDateLessThanEqualAndPaymentDateIsNull(LocalDate.now());
		
		if (expired.isEmpty()) {
			logger.info("No expired journal entries for an email.");			
			return;
		}
		
		logger.info("There are {} expired journal entries.", expired.size());
		
		List<UserAuthorization> recipients = userAuthorizationRepository
				.findByAuthorizationsDescription(RECIPIENTS);
		
		if (recipients.isEmpty()) {
			logger.warn("There are expired journal entries, but the system "
					+ "did not found recipients.");			
			return;
		}
		
		mailer.warnAboutExpiredJournalEntries(expired, recipients);
		
		logger.info("The warning e-mail was send."); 
	}
	
	public byte[] reportByPerson(LocalDate begin, LocalDate end) throws JRException {
		List<JournalEntryPersonStatistics> data = journalEntryRepository.byPerson(begin, end);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("DATE_BEGIN", Date.valueOf(begin));
		parameters.put("DATE_END", Date.valueOf(end));
		parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
			"/reports/journalentries-by-person.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
				new JRBeanCollectionDataSource(data));
				
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public JournalEntry save(JournalEntry journalEntry) {
		validatePerson(journalEntry);		
		return journalEntryRepository.save(journalEntry);
	}
	
	public JournalEntry update(Long id, JournalEntry journalEntry) {
		JournalEntry savedJournalEntry = getExistingJournalEntry(id);
		if (!journalEntry.getPerson().equals(savedJournalEntry.getPerson())) {
			validatePerson(journalEntry);
		}
		
		BeanUtils.copyProperties(journalEntry, savedJournalEntry, "id");
		
		return journalEntryRepository.save(savedJournalEntry);
	}
	
	private void validatePerson(JournalEntry journalEntry) {
		Person person = null;
		
		if (journalEntry.getPerson() != null) {
			person = personRepository.getById(journalEntry.getPerson().getId());
		}
		
		if (person == null || person.isInactive()) {
			throw new NonexistentOrInactivePersonException();
		}		
	}
	
	
	private JournalEntry getExistingJournalEntry(Long id) {
		JournalEntry savedJournalEntry = journalEntryRepository.findById(id).orElse(null);
		if (savedJournalEntry == null) {
			throw new IllegalArgumentException();
		}
		return savedJournalEntry;
	}
	
}
