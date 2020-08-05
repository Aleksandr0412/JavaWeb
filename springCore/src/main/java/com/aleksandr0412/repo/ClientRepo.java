package com.aleksandr0412.repo;

import com.aleksandr0412.entities.Client;
import com.aleksandr0412.service.ConnectionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ClientRepo {
    private ConnectionService cs;

    @Autowired
    public ClientRepo(ConnectionService cs) {
        this.cs = cs;
    }

    public Client getById(long id) {
        Client client;
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            client = session.get(Client.class, id);
            session.getTransaction().commit();
        }
        return client;
    }

    public List<Client> getAll() {
        List<Client> clients;
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            TypedQuery<Client> query = session.createQuery("SELECT c FROM Client c", Client.class);
            clients = query.getResultList();
            session.getTransaction().commit();
        }
        return clients;
    }

    public void save(Client client) {
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }

    }

    public void save(List<Client> clients) {
        try (Session session = cs.getSession()) {
            session.beginTransaction();
            for (Client client : clients) {
                session.save(client);
            }
            session.getTransaction().commit();
        }
    }
}
