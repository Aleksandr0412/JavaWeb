package com.aleksandr0412.springdata;

import com.aleksandr0412.springdata.exceptions.ResourceNotFoundException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.aleksandr0412.springdata.common.Constants.EXCHANGE_FOR_READY_ORDERS;
import static com.aleksandr0412.springdata.common.Constants.ORDERS_QUEUE;

public class ConsoleManagementApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        List<Long> orders = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(" [*] Ожидание новых заказов");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" Получен заказ №" + message);
            orders.add(Long.parseLong(message));
            System.out.println("\nВведите команду \"exit\" для выхода: ");
            while (orders.size() != 0) {
                String input = sc.nextLine();
                if (input.equals("exit")) {
                    break;
                }
                if (input.startsWith("/готово")) {
                    Long orderId = Long.parseLong(input.split("  *")[1]);
                    if (orders.contains(orderId)) {
                        orders.remove(orderId);
                        System.out.println("Заказ №" + orderId + " подготовлен!");
                        channel.basicPublish(EXCHANGE_FOR_READY_ORDERS, "", null, orderId.toString().getBytes());
                    } else {
                        throw new ResourceNotFoundException("Такого заказа не существует!");
                    }
                } else {
                    System.out.println("Введите заново!");
                }
            }
        };

        channel.basicConsume(ORDERS_QUEUE, true, deliverCallback, consumerTag -> {
        });
    }
}