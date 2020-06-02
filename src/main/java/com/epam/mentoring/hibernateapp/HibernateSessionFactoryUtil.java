package com.epam.mentoring.hibernateapp;

import com.epam.mentoring.hibernateapp.model.Categories;
import com.epam.mentoring.hibernateapp.model.Products;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Categories.class);
                configuration.addAnnotatedClass(Products.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                                  .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return sessionFactory;
    }
}
