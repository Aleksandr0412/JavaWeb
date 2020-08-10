package com.aleksandr0412.springMVC.service;

import com.aleksandr0412.springMVC.entities.Product;
import com.aleksandr0412.springMVC.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ProductRepo pr;

    @Autowired
    public ProductService(ProductRepo pr) {
        this.pr = pr;
    }

    public void save(Product product) {
        pr.save(product);
    }

    public void save(List<Product> products) {
        pr.save(products);
    }

    public Product getById(long id) {
        return pr.getById(id);
    }

    public List<Product> getAll() {
        return pr.getAll();
    }
}
