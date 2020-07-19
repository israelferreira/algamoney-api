package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, String> phone;
	public static volatile SingularAttribute<Contact, Person> person;
	public static volatile SingularAttribute<Contact, String> name;
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, String> email;

	public static final String PHONE = "phone";
	public static final String PERSON = "person";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

