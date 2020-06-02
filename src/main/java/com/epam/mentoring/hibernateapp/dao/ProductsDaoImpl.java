package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.HibernateSessionFactoryUtil;
import com.epam.mentoring.hibernateapp.model.ProductId;
import com.epam.mentoring.hibernateapp.model.Products;
import com.epam.mentoring.hibernateapp.model.Products_;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductsDaoImpl implements ProductsDao {
    public Products findById(ProductId id) {
        Products products;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
            Root<Products> categoriesRoot = criteria.from(Products.class);
            Predicate idPredicate = builder.equal(categoriesRoot.get(Products_.id), id);
            criteria.where(idPredicate);
            TypedQuery<Products> query = session.createQuery(criteria);
            products = query.getSingleResult();
        }
        return products;
    }

    public List<Products> findAll() {
        List<Products> products;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            products = session.createQuery("SELECT a FROM Products  a", Products.class).getResultList();
        }
        return products;
    }

    public void delete(ProductId id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Products products = session.load(Products.class, id);
            session.delete(products);
            transaction.commit();
        }
    }

    public List<Products> findByCategoryId(Long id) {
        
        List<Products> products;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("Products.findByCategoryId");
            query.setParameter("categoryId", id);
            products = query.getResultList();
        }
        return products;
    }

    public void create(Products products) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(products);
            transaction.commit();
        }
    }

    public void update(Products products) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(products);
            transaction.commit();
        }
    }

    public void deleteAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM products");
            query.executeUpdate();
        }
    }
}
