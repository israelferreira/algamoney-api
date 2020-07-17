package com.algaworks.algamoney.api.model;

public enum JournalEntryType {
	/*
	 RECEITA = INCOME
	 DESPESA = OUTCOME
	*/
	
	RECEITA("Receita"),
	DESPESA("Despesa");
	
	private final String description;

	private JournalEntryType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
