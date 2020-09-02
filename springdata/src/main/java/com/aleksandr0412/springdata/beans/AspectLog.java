package com.aleksandr0412.springdata.beans;

import lombok.Getter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Getter
@Component
@Aspect
public class AspectLog {
    private int bookServiceCount, ordersServiceCount, userServiceCount, orderItemServiceCount;

    @After("execution(public * com.aleksandr0412.springdata.services.BookService.*(..))")
    public void afterBookService() {
        bookServiceCount++;
    }

    @After("execution(public * com.aleksandr0412.springdata.services.OrderService.*(..))")
    public void afterOrdersService() {
        ordersServiceCount++;
    }

    @After("execution(public * com.aleksandr0412.springdata.services.UserService.*(..))")
    public void afterUserService() {
        userServiceCount++;
    }
    @After("execution(public * com.aleksandr0412.springdata.services.OrderItemService.*(..))")
    public void afterOrderItemsService() {
        orderItemServiceCount++;
    }
}
