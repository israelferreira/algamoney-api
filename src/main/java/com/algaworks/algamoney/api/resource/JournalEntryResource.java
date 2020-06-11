package com.algaworks.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Error;
import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.repository.JournalEntryRepository;
import com.algaworks.algamoney.api.repository.filter.JournalEntryFilter;
import com.algaworks.algamoney.api.repository.projection.JournalEntrySummary;
import com.algaworks.algamoney.api.service.JournalEntryService;
import com.algaworks.algamoney.api.service.exception.NonexistentOrInactivePersonException;

@RestController
@RequestMapping("/journalentries")
public class JournalEntryResource {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and #oauth2.hasScope('read')")
	public Page<JournalEntry> search(JournalEntryFilter journalEntryFilter, Pageable pageable) {
		return journalEntryRepository.filter(journalEntryFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and #oauth2.hasScope('read')")
	public ResponseEntity<JournalEntry> searchById(@PathVariable Long id) {
		JournalEntry journalEntry = journalEntryRepository.findById(id).orElse(null);		
		return journalEntry != null ? ResponseEntity.ok(journalEntry) : ResponseEntity.notFound().build();		
	}
	
	@GetMapping(params = "summary")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and #oauth2.hasScope('read')")
	public Page<JournalEntrySummary> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable) {
		return journalEntryRepository.summarize(journalEntryFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_JOURNALENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<JournalEntry> create(@Valid @RequestBody JournalEntry journalEntry, HttpServletResponse response) {
		JournalEntry savedJournalEntry = journalEntryService.save(journalEntry);		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedJournalEntry.getId()));				
		return ResponseEntity.status(HttpStatus.CREATED).body(savedJournalEntry);
	}
		
	@ExceptionHandler({ NonexistentOrInactivePersonException.class })
	public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex) {
		String userMessage = messageSource.getMessage("person.nonexistent-or-inactive", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_JOURNALENTRY') and #oauth2.hasScope('write')")
	public void remove(@PathVariable Long id) {
		journalEntryRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_JOURNALENTRY')")
	public ResponseEntity<JournalEntry> atualizar(@PathVariable Long id, @Valid @RequestBody JournalEntry journalEntry) {
		try {
			JournalEntry savedJournalEntry = journalEntryService.update(id, journalEntry);
			return ResponseEntity.ok(savedJournalEntry);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
