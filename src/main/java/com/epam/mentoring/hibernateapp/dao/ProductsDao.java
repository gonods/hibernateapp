package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;

import java.util.List;

public interface ProductsDao {
    Products findById(ProductId id);

    List<Products> findAll();

    List<Products> findByCategoryId(Long id);

    void delete(ProductId id);

    void deleteAll();

    void create(Products products);

    void update(Products products);
}
