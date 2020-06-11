package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.JournalEntryRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.exception.NonexistentOrInactivePersonException;


@Service
public class JournalEntryService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
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
