package com.aleksandr0412;

import com.aleksandr0412.configs.Config;
import com.aleksandr0412.entities.Client;
import com.aleksandr0412.entities.Product;
import com.aleksandr0412.service.ClientService;
import com.aleksandr0412.service.ConnectionService;
import com.aleksandr0412.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        ConnectionService cs = context.getBean("cs", ConnectionService.class);
        PrepareDataApp.forcePrepareData();
        ClientService clientService = context.getBean("clientService", ClientService.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        Client c = new Client("Alex", 21);
        Product p = new Product("panamera", 5_000_000L);
        clientService.save(c);
        productService.save(p);

        System.out.println("Пользователь с id = 1: " + clientService.getById(1));
        System.out.println("Продукт с id = 2: " + productService.getById(2));
        System.out.println();

        clientService.getAll().forEach(System.out::println);
        System.out.println();
        productService.getAll().forEach(System.out::println);

        context.close();
    }
}

