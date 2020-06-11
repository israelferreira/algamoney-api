package com.algaworks.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.UserAuthorization;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, Long> {
	
	public Optional<UserAuthorization> findByEmail(String email);
	
}
