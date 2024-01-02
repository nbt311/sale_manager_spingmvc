package com.example.productmanager.repository;

import com.example.productmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category , Long> {
}
