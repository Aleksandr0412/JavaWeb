package com.aleksandr0412.springboot.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;


//    @Override
//    public String toString() {
//        return "Client{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }


}