package com.algaworks.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Page<Person> findByNameContaining(String name, Pageable pageable);

}
