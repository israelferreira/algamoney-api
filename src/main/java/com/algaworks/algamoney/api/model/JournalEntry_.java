package com.algaworks.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JournalEntry.class)
public abstract class JournalEntry_ {

	public static volatile SingularAttribute<JournalEntry, JournalEntryType> entryType;
	public static volatile SingularAttribute<JournalEntry, BigDecimal> amount;
	public static volatile SingularAttribute<JournalEntry, String> observation;
	public static volatile SingularAttribute<JournalEntry, Person> person;
	public static volatile SingularAttribute<JournalEntry, LocalDate> dueDate;
	public static volatile SingularAttribute<JournalEntry, String> description;
	public static volatile SingularAttribute<JournalEntry, Long> id;
	public static volatile SingularAttribute<JournalEntry, LocalDate> paymentDate;
	public static volatile SingularAttribute<JournalEntry, Category> category;

	public static final String ENTRY_TYPE = "entryType";
	public static final String AMOUNT = "amount";
	public static final String OBSERVATION = "observation";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String CATEGORY = "category";

}

