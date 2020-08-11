package com.aleksandr0412.springboot.controller;

import com.aleksandr0412.springboot.entities.Product;
import com.aleksandr0412.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProducts", productService.getAll());
        return "all_products";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("frontProduct", productService.getById(id));
        return "product_page";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("frontProduct", productService.getById(id));
        return "edit_product";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product modifiedProduct) {
        productService.save(modifiedProduct);
        return "redirect:/products/all";
    }

}