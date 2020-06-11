package com.algaworks.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonResource {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savedPerson = personRepository.save(person);		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedPerson.getId()));				
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
	public ResponseEntity<Person> searchById(@PathVariable Long id) {		
		Person person = personRepository.findById(id).orElse(null);		
		return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}
		
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")	
	public Page<Person> search(@RequestParam(required = false, defaultValue = "") String name, Pageable pageable) {
		return personRepository.findByNameContaining(name, pageable);
	}
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and #oauth2.hasScope('write')")
	public void remove(@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
		Person savedPerson = personService.update(id, person);
		return ResponseEntity.ok(savedPerson);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
	public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean inactive) {
		personService.updateActiveProperty(id, inactive);
	}
	
	
	
}
