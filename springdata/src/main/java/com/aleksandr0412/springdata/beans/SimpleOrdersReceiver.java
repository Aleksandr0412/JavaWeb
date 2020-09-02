package com.aleksandr0412.springdata.beans;

import com.aleksandr0412.springdata.entities.Order;
import com.aleksandr0412.springdata.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimpleOrdersReceiver {
    private OrderService orderService;

    public void receiveMessage(byte[] message) {
        Long orderId = Long.parseLong(new String(message));
        System.out.println("Заказ №" + orderId.toString() + " готов");
        Order order = orderService.findById(orderId);
        order.setOrderStatus(Order.OrderStatus.READY);
        orderService.saveOrUpdate(order);
    }
}