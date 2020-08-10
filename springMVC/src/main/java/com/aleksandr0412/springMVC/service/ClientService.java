package com.aleksandr0412.springMVC.service;

import com.aleksandr0412.springMVC.entities.Client;
import com.aleksandr0412.springMVC.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientService {
    private ClientRepo cr;

    @Autowired
    public ClientService(ClientRepo cr) {
        this.cr = cr;
    }

    public void save(Client client) {
        cr.save(client);
    }

    public void save(List<Client> clients) {
        cr.save(clients);
    }

    public Client getById(long id) {
        return cr.getById(id);
    }

    public List<Client> getAll() {
        return cr.getAll();
    }
}
