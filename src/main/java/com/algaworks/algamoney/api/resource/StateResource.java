package com.algaworks.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.State;
import com.algaworks.algamoney.api.repository.StateRepository;

@RestController
@RequestMapping("/states")
public class StateResource {

	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<State> getAll() {
		return stateRepository.findAll();
	}
}
