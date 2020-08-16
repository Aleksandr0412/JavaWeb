package com.aleksandr0412.springdata.repositories;

import com.aleksandr0412.springdata.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}