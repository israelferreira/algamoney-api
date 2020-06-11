package com.algaworks.algamoney.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.UserAuthorization;
import com.algaworks.algamoney.api.repository.UserAuthorizationRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAuthorizationRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserAuthorization> userOptional = userRepository.findByEmail(email);
		UserAuthorization user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(email, user.getPassword(), getAuthorizations(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorizations(UserAuthorization user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getAuthorizations().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}
