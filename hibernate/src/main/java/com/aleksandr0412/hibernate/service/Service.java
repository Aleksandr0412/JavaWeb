package com.aleksandr0412.hibernate.service;

import com.aleksandr0412.hibernate.entities.Client;
import com.aleksandr0412.hibernate.entities.Product;
import com.aleksandr0412.hibernate.entities.Purchases;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private ConnectionService connectionService;

    public Service(ConnectionService sessionFactory) {
        this.connectionService = sessionFactory;
    }

    public List<Product> getProductsByConsumer(String name) {
        connectionService.startSession();
        Query query = connectionService.getSession().createQuery("SELECT c FROM Client c WHERE c.name = :name", Client.class);
        query.setParameter("name", name);
        Client consumer = (Client) query.getSingleResult();
//        List<Product> products = consumer.getProducts();
        List<Product> products = new ArrayList<>();
        for (Product product : consumer.getProducts())
            products.add(product);
        connectionService.endSession();
        return products;

    }

    public List<Client> getConsumersByProductTitle(String name) {
        connectionService.startSession();
        Query query = connectionService.getSession().createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
        query.setParameter("name", name);
        Product product = (Product) query.getSingleResult();
//        List<Client> clients = product.getClients();
        List<Client> clients = new ArrayList<>();
        for(Client client : product.getClients())
            clients.add(client);
        connectionService.endSession();
        return clients;
    }

    public void deleteConsumer(String name) {
        connectionService.startSession();
        Query query = connectionService.getSession().createQuery("DELETE FROM Client c WHERE c.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
        connectionService.endSession();
    }

    public void deleteProduct(String name) {
        connectionService.startSession();
        Query query = connectionService.getSession().createQuery("DELETE FROM Product p WHERE p.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
        connectionService.endSession();
    }

    public void buy(long clientId, long productId) {
        connectionService.startSession();
        Client client = (Client) connectionService.getSession().get(Client.class, clientId);
        Product product = (Product) connectionService.getSession().get(Product.class, productId);
        long price = product.getCurrentPrice();
        connectionService.getSession().save(new Purchases(price, client.getId(), product.getId()));
        connectionService.endSession();
    }

}
