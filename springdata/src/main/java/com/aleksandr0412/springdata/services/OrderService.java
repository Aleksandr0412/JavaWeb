package com.aleksandr0412.springdata.services;

import com.aleksandr0412.springdata.beans.Cart;
import com.aleksandr0412.springdata.entities.Order;
import com.aleksandr0412.springdata.entities.OrderItem;
import com.aleksandr0412.springdata.entities.User;
import com.aleksandr0412.springdata.exceptions.ResourceNotFoundException;
import com.aleksandr0412.springdata.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemService orderItemService;
    private UserService userService;
    private Cart cart;

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Transactional
    public Order processOrder(String userName) {
        User user = userService.findByUsername(userName).orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь %s не найден", userName)));
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getTotalPrice());
        order.setOrderStatus(Order.OrderStatus.IN_PROCESSING);
        order = saveOrUpdate(order);
        for (OrderItem orderItem : cart.getOrderItems()) {
            orderItem.setOrder(order);
            orderItemService.saveOrUpdate(orderItem);
        }
        cart.clearCart();
        return order;
    }

}