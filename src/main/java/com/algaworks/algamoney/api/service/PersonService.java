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
	
	public Person save(Person person) {
		person.getContacts().forEach(c -> c.setPerson(person));
		return personRepository.save(person);		
	}
	
	public Person update(Long id, Person person) {
		Person savedPerson = findPersonById(id);
		
		savedPerson.getContacts().clear();
		savedPerson.getContacts().addAll(person.getContacts());
		savedPerson.getContacts().forEach(c -> c.setPerson(savedPerson));
		
		BeanUtils.copyProperties(person, savedPerson, "id", "contacts");
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
