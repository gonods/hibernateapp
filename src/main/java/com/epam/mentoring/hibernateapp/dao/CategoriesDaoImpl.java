package com.epam.mentoring.hibernateapp.dao;

import com.epam.mentoring.hibernateapp.HibernateSessionFactoryUtil;
import com.epam.mentoring.hibernateapp.model.Categories;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoriesDaoImpl implements CategoriesDao {
    public Categories findById(Long id) {
        Categories category;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories> criteria = builder.createQuery(Categories.class);
            Root<Categories> categoriesRoot = criteria.from(Categories.class);
            Predicate idPredicate = builder.equal(categoriesRoot.get("id"), id);
            criteria.where(idPredicate);
            TypedQuery<Categories> query = session.createQuery(criteria);
            category = query.getSingleResult();
        }
        return category;
    }

    public List<Categories> findAll() {
        List<Categories> categories;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories> query = builder.createQuery(Categories.class);
            Root<Categories> root = query.from(Categories.class);
            root.fetch("products", JoinType.LEFT);
            query.select(root);
            categories = session.createQuery(query).getResultList();
        }
        return categories;
    }

    public void delete(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(categories);
            transaction.commit();
        }
    }

    public void create(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(categories);
            transaction.commit();
        }
    }

    public void update(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(categories);
            transaction.commit();
        }
    }

    public void deleteAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM categories");
            query.executeUpdate();
        }
    }

    public Categories findByName(String name) {
        Categories categories;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("Categories.byName");
            categories = (Categories) query.getResultList().get(0);
        }
        return categories;
    }
}
