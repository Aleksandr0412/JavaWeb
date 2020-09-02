package com.aleksandr0412.springdata.services;
import com.aleksandr0412.springdata.entities.OrderItem;
import com.aleksandr0412.springdata.exceptions.ResourceNotFoundException;
import com.aleksandr0412.springdata.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void saveOrUpdate(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}