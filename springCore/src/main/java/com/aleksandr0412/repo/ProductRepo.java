package com.aleksandr0412.repo;

import com.aleksandr0412.entities.Product;
import com.aleksandr0412.service.ConnectionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ProductRepo {
    private ConnectionService cs;

    @Autowired
    public ProductRepo(ConnectionService cs) {
        this.cs = cs;
    }

    public Product getById(long id) {
        Product product;
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> getAll() {
        List<Product> products;
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            TypedQuery<Product> query = session.createQuery("SELECT p FROM Product p", Product.class);
            products = query.getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public void save(Product product) {
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }

    }

    public void save(List<Product> products) {
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            for (Product product : products) {
                session.save(product);
            }
            session.getTransaction().commit();
        }
    }
}
