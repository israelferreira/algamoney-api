package com.algaworks.algamoney.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.City;

public interface CityRepository extends JpaRepository<City, Long>{
	
	List<City> findByStateId(Long stateId);
}
