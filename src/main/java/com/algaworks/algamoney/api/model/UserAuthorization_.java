package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAuthorization.class)
public abstract class UserAuthorization_ {

	public static volatile SingularAttribute<UserAuthorization, String> password;
	public static volatile SingularAttribute<UserAuthorization, String> name;
	public static volatile ListAttribute<UserAuthorization, Authorization> authorizations;
	public static volatile SingularAttribute<UserAuthorization, Long> id;
	public static volatile SingularAttribute<UserAuthorization, String> email;

	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String AUTHORIZATIONS = "authorizations";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

