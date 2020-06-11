package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;

import com.algaworks.algamoney.api.model.Category;


public class JournalEntryCategoryStatistics {
	
	private Category category;
	
	private BigDecimal amount;

	public JournalEntryCategoryStatistics(Category category, BigDecimal amount) {
		this.category = category;
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	

}
