package com.aleksandr0412.springboot.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Long price;

//    @Override
//    public String toString() {
//        return "Product{" +
//                "title='" + title + '\'' +
//                ", price=" + price +
//                '}';
//    }
}