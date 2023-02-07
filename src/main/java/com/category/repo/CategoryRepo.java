package com.category.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.category.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
