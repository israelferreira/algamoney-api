package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.model.JournalEntryType;

public class JournalEntryPerDayStatistics {

	private JournalEntryType entryType;
	
	private LocalDate day;
	
	private BigDecimal amount;

	public JournalEntryPerDayStatistics(JournalEntryType entryType, LocalDate day, BigDecimal amount) {
		this.entryType = entryType;
		this.day = day;
		this.amount = amount;
	}

	public JournalEntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(JournalEntryType entryType) {
		this.entryType = entryType;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}
