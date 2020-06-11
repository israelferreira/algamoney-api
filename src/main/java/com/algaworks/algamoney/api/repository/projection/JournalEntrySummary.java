package com.algaworks.algamoney.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.model.JournalEntryType;

public class JournalEntrySummary {
	
	private long id;
	private String description;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private BigDecimal amount;
	private JournalEntryType entryType;
	private String category;
	private String person;
	
	
	public JournalEntrySummary(long id, String description, LocalDate dueDate, LocalDate paymentDate, BigDecimal amount,
			JournalEntryType entryType, String category, String person) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.entryType = entryType;
		this.category = category;
		this.person = person;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public JournalEntryType getEntryType() {
		return entryType;
	}


	public void setEntryType(JournalEntryType entryType) {
		this.entryType = entryType;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getPerson() {
		return person;
	}


	public void setPerson(String person) {
		this.person = person;
	}
	
}
