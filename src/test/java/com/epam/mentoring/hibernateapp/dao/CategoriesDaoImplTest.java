package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.model.Categories;
import com.epam.mentoring.hibernateapp.model.Condition;
import com.epam.mentoring.hibernateapp.model.Manufacturer;
import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CategoriesDaoImplTest {
    ProductsDaoImpl productsDao = new ProductsDaoImpl();
    CategoriesDaoImpl categoriesDao = new CategoriesDaoImpl();


    @BeforeEach
    void prepare() {
        productsDao.deleteAll();
        categoriesDao.deleteAll();
    }

    @Test
    void findById() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        ProductId id = new ProductId("Штаны зеленые", (long) 12);
        Products products1 = new Products(id, 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products1);
        Products products2 = new Products(new ProductId("Штаны красные", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products2);
        Categories categories1 = categoriesDao.findById(categories.get(1).getId());

        assertEquals(categories.get(1).getName(), categories1.getName());

    }

    @Test
    void findAll() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();

        assertEquals(3, categories.size());
    }

    @Test
    void delete() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        categoriesDao.delete(categories.get(2));
        categories = categoriesDao.findAll();

        assertEquals(2, categories.size());
    }

    @Test
    void create() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        List<Categories> categories = categoriesDao.findAll();

        assertEquals(1, categories.size());
    }

    @Test
    void update() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        List<Categories> categories = categoriesDao.findAll();
        Categories categories1 = categories.get(0);
        categories1.setName("Желетка2");
        categoriesDao.update(categories1);
        categories1 = categoriesDao.findById(categories1.getId());

        assertEquals("Желетка2", categories1.getName());
    }

    @Test
    void deleteAll() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.deleteAll();
        List<Categories> categories = categoriesDao.findAll();

        assertEquals(0, categories.size());
    }

    @Test
    void findByName() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        Categories categories = categoriesDao.findByName("Желетка");

        assertEquals("Желетка", categories.getName());
    }

    @Test
    void nPlusOneProblem() {
        Logger logger = Logger.getLogger(CategoriesDaoImplTest.class);
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        Products products0 = new Products(new ProductId("Штаны желтые", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(0));
        productsDao.create(products0);
        Products products1 = new Products(new ProductId("Штаны зеленые", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(0));
        productsDao.create(products1);
        Products products2 = new Products(new ProductId("Штаны красные", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(0));
        productsDao.create(products2);

        products0 = new Products(new ProductId("Штаны желтые2", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products0);
        products1 = new Products(new ProductId("Штаны зеленые2", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products1);
        products2 = new Products(new ProductId("Штаны красные2", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products2);

        products0 = new Products(new ProductId("Штаны желтые3", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(2));
        productsDao.create(products0);
        products1 = new Products(new ProductId("Штаны зеленые3", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(2));
        productsDao.create(products1);
        products2 = new Products(new ProductId("Штаны красные3", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(2));
        productsDao.create(products2);
        categories = categoriesDao.findAll();
        for (Categories cat : categories) {
            logger.info("категори");
            logger.info(cat.getName());
            List<Products> products = cat.getProducts();
            logger.info("продукты");
            for (Products product : products) {
                logger.info(product.getId().getProductName());

            }
        }
        assertFalse(categories.isEmpty());

    }
}