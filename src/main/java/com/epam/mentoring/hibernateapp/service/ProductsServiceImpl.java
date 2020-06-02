package com.epam.mentoring.hibernateapp.service;

import com.epam.mentoring.hibernateapp.dao.ProductsDaoImpl;
import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private ProductsDaoImpl productsDao = new ProductsDaoImpl();

    public Products findById(ProductId id) {
        return productsDao.findById(id);
    }

    public List<Products> findAll() {
        return productsDao.findAll();
    }

    public void delete(Products products) {
        productsDao.delete(products.getId());
    }

    public void create(Products products) {
        productsDao.create(products);
    }

    public void update(Products products) {
        productsDao.update(products);
    }
}
