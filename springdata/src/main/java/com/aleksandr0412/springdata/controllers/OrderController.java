package com.aleksandr0412.springdata.controllers;

import com.aleksandr0412.springdata.beans.SimpleOrdersReceiver;
import com.aleksandr0412.springdata.entities.Order;
import com.aleksandr0412.springdata.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static com.aleksandr0412.springdata.common.Constants.EXCHANGE_FOR_ORDERS;
import static com.aleksandr0412.springdata.common.Constants.READY_ORDERS_QUEUE;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/create")
    public String processOrder(Model model, Principal principal) {
        Order order = orderService.processOrder(principal.getName());
        rabbitTemplate.convertAndSend(EXCHANGE_FOR_ORDERS, null, order.getId().toString());
        model.addAttribute("order", order);
        return "order_page";
    }

    @Bean
    public SimpleMessageListenerContainer containerForReadyOrdersQueue(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(READY_ORDERS_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SimpleOrdersReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}