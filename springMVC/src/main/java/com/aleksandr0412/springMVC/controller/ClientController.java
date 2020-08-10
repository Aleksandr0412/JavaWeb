package com.aleksandr0412.springMVC.controller;

import com.aleksandr0412.springMVC.entities.Client;
import com.aleksandr0412.springMVC.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public String getAllClients(Model model) {
        model.addAttribute("frontClients", clientService.getAll());
        return "all_clients";
    }

    @GetMapping("/client/{id}")
    public String getClientById(@PathVariable Long id, Model model) {
        model.addAttribute("frontClient", clientService.getById(id));
        return "client_page";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_client";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients/all";
    }
}