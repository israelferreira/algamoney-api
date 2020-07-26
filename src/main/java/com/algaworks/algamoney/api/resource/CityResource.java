package com.algaworks.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.City;
import com.algaworks.algamoney.api.repository.CityRepository;

@RestController
@RequestMapping("/cities")
public class CityResource {

	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<City> search(@RequestParam Long state) {
		return cityRepository.findByStateId(state);
	}
}
