package com.aleksandr0412.hibernate.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConnectionService {
    private SessionFactory sessionFactory;
    private Session session;

    public ConnectionService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void startSession() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }

    public void endSession() {
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }
}