package com.anisimovdenis.mysql.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DBService {

    private static SessionFactory factory;

    public static void start() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void stop() {
        factory.close();
    }

    public static void prepareDB() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("hibernate_homework.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Optional<List<Product>> getProductsByClientId(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user == null) {
                return Optional.empty();
            }
            System.out.println(user);
            List<Product> products = new ArrayList<>(user.getProducts());
            session.getTransaction().commit();
            return Optional.of(products);
        }
    }

    public static Optional<List<User>> getUsersByProductId(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product == null) {
                return Optional.empty();
            }
            System.out.println(product);
            List<User> users = new ArrayList<>(product.getUsers());
            session.getTransaction().commit();
            return Optional.of(users);
        }
    }

    public static void deleteProductById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void deleteUserById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            System.out.println(user);
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
