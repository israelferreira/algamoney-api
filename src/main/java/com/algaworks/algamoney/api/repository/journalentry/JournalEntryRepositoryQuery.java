package com.algaworks.algamoney.api.repository.journalentry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.dto.JournalEntryCategoryStatistics;
import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.repository.filter.JournalEntryFilter;
import com.algaworks.algamoney.api.repository.projection.JournalEntrySummary;

public interface JournalEntryRepositoryQuery {
	
	public List<JournalEntryCategoryStatistics> byCategory(LocalDate referenceMonth);
	public Page<JournalEntry> filter(JournalEntryFilter journalEntryFilter, Pageable pageable);
	public Page<JournalEntrySummary> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable);

}
