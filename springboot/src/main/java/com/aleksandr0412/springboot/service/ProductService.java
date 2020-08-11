package com.aleksandr0412.springboot.service;

import com.aleksandr0412.springboot.entities.Product;
import com.aleksandr0412.springboot.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo pr;

//    @Autowired
//    public ProductService(ProductRepo pr) {
//        this.pr = pr;
//    }

    public void save(Product product) {
        pr.save(product);
    }

    public void save(List<Product> products) {
        pr.saveAll(products);
    }

    public Product getById(long id) {
        return pr.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<Product> getAll() {
        return pr.findAll();
    }

    public void deleteById(long id) {
        pr.deleteById(id);
    }
}
