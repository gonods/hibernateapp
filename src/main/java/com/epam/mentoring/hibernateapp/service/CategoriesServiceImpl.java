package com.epam.mentoring.hibernateapp.service;

import com.epam.mentoring.hibernateapp.dao.CategoriesDaoImpl;
import com.epam.mentoring.hibernateapp.model.Categories;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private CategoriesDaoImpl categoriesDao = new CategoriesDaoImpl();

    public Categories findById(Long id) {
        return categoriesDao.findById(id);
    }

    public List<Categories> findAll() {
        return categoriesDao.findAll();
    }

    public void delete(Categories categories) {
        categoriesDao.delete(categories);
    }

    public void create(Categories categories) {
        categoriesDao.create(categories);
    }

    public void update(Categories categories) {
        categoriesDao.update(categories);
    }
}
