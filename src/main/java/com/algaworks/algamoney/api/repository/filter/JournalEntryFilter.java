package com.algaworks.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class JournalEntryFilter {

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateTo;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDateFrom() {
		return dueDateFrom;
	}
	public void setDueDateFrom(LocalDate dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}
	public LocalDate getDueDateTo() {
		return dueDateTo;
	}
	public void setDueDateTo(LocalDate dueDateTo) {
		this.dueDateTo = dueDateTo;
	}
	
}
