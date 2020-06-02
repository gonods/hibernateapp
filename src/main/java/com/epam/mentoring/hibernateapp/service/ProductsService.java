package com.epam.mentoring.hibernateapp.service;

import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;

import java.util.List;

public interface ProductsService {
    Products findById(ProductId id);

    List<Products> findAll();

    void delete(Products products);

    void create(Products products);

    void update(Products products);
}
