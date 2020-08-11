package com.aleksandr0412.springboot.controller;

import com.aleksandr0412.springboot.entities.Client;
import com.aleksandr0412.springboot.service.ClientService;
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
    @GetMapping("/delete/{id}")
    public String deleteClientById(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/clients/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        model.addAttribute("frontClient", clientService.getById(id));
        return "edit_client";
    }

    @PostMapping("/edit")
    public String updateClient(@ModelAttribute Client modifiedClient) {
        clientService.save(modifiedClient);
        return "redirect:/clients/all";
    }
}