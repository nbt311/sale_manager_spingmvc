package com.example.productmanager.service;

import com.example.productmanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findByName(String name);
    List<Product> sortByPrice();
    Page<Product> findAllPage(Pageable pageable);
}
