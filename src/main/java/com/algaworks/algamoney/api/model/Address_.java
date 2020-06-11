package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> zipCode;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> neighbourhood;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, String> complement;

	public static final String NUMBER = "number";
	public static final String ZIP_CODE = "zipCode";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String NEIGHBOURHOOD = "neighbourhood";
	public static final String STATE = "state";
	public static final String COMPLEMENT = "complement";

}

