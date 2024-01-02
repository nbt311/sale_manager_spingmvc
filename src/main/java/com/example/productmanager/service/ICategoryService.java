package com.example.productmanager.service;

import com.example.productmanager.model.Category;
import com.example.productmanager.model.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    void save(Category category);
    void delete(Long id);
    Category findById(Long id);
}
