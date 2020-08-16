package com.aleksandr0412.springdata.services;

import com.aleksandr0412.springdata.entities.Order;
import com.aleksandr0412.springdata.exceptions.ResourceNotFoundException;
import com.aleksandr0412.springdata.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}