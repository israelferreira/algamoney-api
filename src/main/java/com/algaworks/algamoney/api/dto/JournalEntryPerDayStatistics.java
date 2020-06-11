package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.model.JournalEntryType;

public class JournalEntryPerDayStatistics {

	private JournalEntryType type;
	
	private LocalDate day;
	
	private BigDecimal amount;

	public JournalEntryPerDayStatistics(JournalEntryType type, LocalDate day, BigDecimal amount) {
		this.type = type;
		this.day = day;
		this.amount = amount;
	}

	public JournalEntryType getType() {
		return type;
	}

	public void setType(JournalEntryType type) {
		this.type = type;
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
