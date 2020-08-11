package com.aleksandr0412.springboot.service;

import com.aleksandr0412.springboot.entities.Client;
import com.aleksandr0412.springboot.repo.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepo cr;
//
//    @Autowired
//    public ClientService(ClientRepo cr) {
//        this.cr = cr;
//    }

    public void save(Client client) {
        cr.save(client);
    }

    public void save(List<Client> clients) {
        cr.saveAll(clients);
    }

    public Client getById(Long id) {
        return cr.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<Client> getAll() {
        return cr.findAll();
    }

    public void deleteById(long id) {
        cr.deleteById(id);
    }
}
