package com.epam.mentoring.hibernateapp.service;

import com.epam.mentoring.hibernateapp.model.Categories;

import java.util.List;

public interface CategoriesService {
    Categories findById(Long id);

    List<Categories> findAll();

    void delete(Categories categories);

    void create(Categories categories);

    void update(Categories categories);
}
