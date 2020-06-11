package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Long>{

}
