package com.aleksandr0412.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component(value = "cs")
public class ConnectionService {
    private final SessionFactory sessionFactory;

    public ConnectionService() {
        this.sessionFactory = buildSessionFactory();
    }

    private SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure("/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void shutdown() {
        sessionFactory.close();
    }
}