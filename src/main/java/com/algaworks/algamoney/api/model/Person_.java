package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, Address> address;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Boolean> active;
	public static volatile SingularAttribute<Person, Long> id;

	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String ID = "id";

}

