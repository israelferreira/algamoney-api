package com.algaworks.algamoney.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.repository.journalentry.JournalEntryRepositoryQuery;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long>, JournalEntryRepositoryQuery {
	
	List<JournalEntry> findByDueDateLessThanEqualAndPaymentDateIsNull(LocalDate date);

}
