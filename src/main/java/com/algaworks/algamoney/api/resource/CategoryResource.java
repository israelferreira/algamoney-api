package com.algaworks.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
	public ResponseEntity<Category> searchById(@PathVariable Long id) {		
		Category category = categoryRepository.findById(id).orElse(null);
		return category != null ? ResponseEntity.ok(category): ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CATEGORY') and #oauth2.hasScope('write')")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category savedCategory = categoryRepository.save(category);		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedCategory.getId()));				
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
	
	
}
