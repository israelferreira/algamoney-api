package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;

import com.algaworks.algamoney.api.model.JournalEntryType;
import com.algaworks.algamoney.api.model.Person;

public class JournalEntryPersonStatistics {

	private JournalEntryType entryType;
	
	private Person person;
	
	private BigDecimal amount;

	public JournalEntryPersonStatistics(JournalEntryType entryType, Person person, BigDecimal amount) {
		this.entryType = entryType;
		this.person = person;
		this.amount = amount;
	}

	public JournalEntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(JournalEntryType entryType) {
		this.entryType = entryType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
		
}
