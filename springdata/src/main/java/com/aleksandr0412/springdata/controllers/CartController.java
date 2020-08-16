package com.aleksandr0412.springdata.controllers;

import com.aleksandr0412.springdata.beans.Cart;
import com.aleksandr0412.springdata.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    @GetMapping
    public String showCartPage(Model model) {
        model.addAttribute("orderItems", cart.getOrderItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart_page";
    }

    @GetMapping("/{id}")
    public String addBookToCart(@PathVariable Long id) {
        cart.addBookToCart(id);
        return "redirect:/books";
    }

    @GetMapping("/delete/{index}")
    public String deleteBookFromCart(@PathVariable int index) {
        cart.deleteBookFromCart(index);
        return "redirect:/cart";
    }

    @GetMapping("/delete")
    public String clearCart() {
        cart.clearCart();
        return "redirect:/cart";
    }

    @GetMapping("/process")
    public String processOrder(Model model, Principal principal) {
        Order order = cart.processOrder(principal.getName());
        cart.clearCart();
        model.addAttribute("order", order);
        return "order_page";
    }
}