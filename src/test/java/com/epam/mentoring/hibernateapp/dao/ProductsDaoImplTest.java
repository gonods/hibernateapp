package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.model.Categories;
import com.epam.mentoring.hibernateapp.model.Condition;
import com.epam.mentoring.hibernateapp.model.Manufacturer;
import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;
import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsDaoImplTest {
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
                          new Manufacturer("company name", "RUSSIA"), categories.get(0));
        productsDao.create(products1);
        Products products2 = new Products(new ProductId("Штаны красные", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products2);

        List<Products> products = productsDao.findAll();
        Products product = products.get(0);
        Products productsTest = productsDao.findById(product.getId());

        assertEquals(product.getId().getProductName(), productsTest.getId().getProductName());
    }

    @Test
    void findAll() {
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
        List<Products> products = productsDao.findAll();

        assertEquals(2, products.size());
    }

    @Test
    void delete() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        Products products1 = new Products(new ProductId("Штаны зеленые", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products1);
        List<Products> products = productsDao.findAll();
        productsDao.delete(products.get(0).getId());
        products = productsDao.findAll();

        assertEquals(0, products.size());
    }

    @Test
    void create() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        Products products1 = new Products(new ProductId("Штаны зеленые", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(products1);
        List<Products> products = productsDao.findAll();

        assertEquals(1, products.size());
    }

    @Test
    void update() {
        categoriesDao.create(new Categories("Желетка", (long) 10));
        categoriesDao.create(new Categories("Штаны", (long) 11));
        categoriesDao.create(new Categories("Чай", (long) 15));
        List<Categories> categories = categoriesDao.findAll();
        Products product = new Products(new ProductId("Штаны красные", (long) 12), 200.00, Condition.NEW,
                          new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productsDao.create(product);
        product = productsDao.findAll().get(0);
        product.setCondition(Condition.USED);
        productsDao.update(product);
        product = productsDao.findById(product.getId());

        assertEquals(Condition.USED, product.getCondition());
    }

    @Test
    void deleteAll() {
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
        productsDao.deleteAll();
        List<Products> products = productsDao.findAll();

        assertEquals(0, products.size());
    }

    @Test
    void findByCategoryId() {
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

        List<Products> products = productsDao.findByCategoryId(categories.get(1).getId());

        assertEquals(2, products.size());
    }

    @Test
    void cache() {
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
        List<Products> products = productsDao.findByCategoryId(categories.get(1).getId());

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                          .getCache("com.epam.mentoring.hibernateapp.model.Products").getSize();

        assertTrue(size > 0);
    }
}