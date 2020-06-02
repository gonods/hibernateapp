package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.model.Categories;

import java.util.List;

public interface CategoriesDao {
    Categories findById(Long id);

    Categories findByName(String name);

    List<Categories> findAll();

    void delete(Categories categories);

    void create(Categories categories);

    void update(Categories categories);

    void deleteAll();
}
