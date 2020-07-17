package com.algaworks.algamoney.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.dto.JournalEntryPersonStatistics;
import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.JournalEntryRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.exception.NonexistentOrInactivePersonException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JournalEntryService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
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
			person = personRepository.getOne(journalEntry.getPerson().getId());
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
