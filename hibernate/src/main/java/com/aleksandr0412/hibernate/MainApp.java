package com.aleksandr0412.hibernate;

import com.aleksandr0412.hibernate.service.ConnectionService;
import com.aleksandr0412.hibernate.service.Service;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {

    private static Service service;

    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        ConnectionService connectionService = new ConnectionService(factory);
        service = new Service(connectionService);

        try {
            MainApp.parser();
        } catch (IOException e) {
            connectionService.endSession();
        }
    }

    public static void parser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while (!input.equals("exit")) {
            input = reader.readLine();
            String[] commands = input.split(" ");
            switch (commands[0]) {
                case "/showProductsByConsumer":
                    service.getProductsByConsumer(commands[1]);
                    break;
                case "/showConsumersByProductTitle":
                    service.getConsumersByProductTitle(commands[1]);
                    break;
                case "/deleteConsumer":
                    service.deleteConsumer(commands[1]);
                    break;
                case "/deleteProduct":
                    service.deleteProduct(commands[1]);
                    break;
                case "/buy":
                    service.buy(Long.parseLong(commands[1]), Long.parseLong(commands[2]));
                    break;
            }
        }
    }
}