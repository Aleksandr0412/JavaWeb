package com.aleksandr0412.springdata.controllers;

import com.aleksandr0412.springdata.beans.AspectLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stat")
@AllArgsConstructor
public class AspectController {
    private AspectLog log;

    @GetMapping
    public String countAllMethodCalls(Model model) {
        model.addAttribute("bookServiceCount", log.getBookServiceCount());
        model.addAttribute("ordersServiceCount", log.getOrdersServiceCount());
        model.addAttribute("userServiceCount", log.getUserServiceCount());
        model.addAttribute("orderItemServiceCount", log.getOrderItemServiceCount());
        return "stat-page";
    }
}
