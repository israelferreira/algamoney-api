package com.algaworks.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.algamoney.api.model.UserAuthorization;

public class SystemUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private UserAuthorization userAuthorization;
	
	public SystemUser(UserAuthorization userAuthorization, Collection<? extends GrantedAuthority> authorities) {		
		super(userAuthorization.getEmail(), userAuthorization.getPassword(), authorities);
		this.userAuthorization = userAuthorization;
	}

	public UserAuthorization getUserAuthorization() {
		return userAuthorization;
	}
	

}
