package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person update(Long id, Person person) {
		Person savedPerson = findPersonById(id);	
		BeanUtils.copyProperties(person, savedPerson, "id");
		return personRepository.save(savedPerson);		
	}	

	public void updateActiveProperty(Long id, Boolean active) {
		Person savedPerson = findPersonById(id);
		savedPerson.setActive(active);
		personRepository.save(savedPerson);
	}
	
	private Person findPersonById(Long id) {
		Person savedPerson = personRepository.findById(id).orElse(null);
		if(savedPerson == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return savedPerson;
	}
		
}
