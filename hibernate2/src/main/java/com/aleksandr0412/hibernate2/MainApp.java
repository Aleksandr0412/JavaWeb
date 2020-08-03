package com.aleksandr0412.hibernate2;

import com.aleksandr0412.hibernate2.entities.Lot;
import com.aleksandr0412.hibernate2.entities.User;
import com.aleksandr0412.hibernate2.locks.OptimisticLock;
import com.aleksandr0412.hibernate2.locks.PessimisticLock;
import com.aleksandr0412.hibernate2.service.Service;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        CountDownLatch cdl = new CountDownLatch(8);
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Service service = new Service(sessionFactory);
//        checkOptimistic(service, cdl, sessionFactory);
        checkPessimistic(service, cdl, sessionFactory);
        checkSum(service);

//        Optimistic: 18272
//        Pessimistic: 5038

    }

    public static void checkOptimistic(Service service, CountDownLatch cdl, SessionFactory sessionFactory) {
        service.startSession();
        List<User> userList = service.getUsers();
        service.commitAndClose();
        long time = System.currentTimeMillis();
        for (User user : userList) {
            new OptimisticLock(user, cdl, sessionFactory).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - time));
    }

    public static void checkPessimistic(Service service, CountDownLatch cdl, SessionFactory sessionFactory) {
        service.startSession();
        List<User> users = service.getUsers();
        service.commitAndClose();

        long time = System.currentTimeMillis();
        for (User user : users) {
            new PessimisticLock(user, cdl, sessionFactory).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - time));
    }

    public static void checkSum(Service service) {
        long sum = 0;
        service.startSession();
        List<Lot> lots = service.getLots();
        for (Lot lot : lots) {
            sum += lot.getCurrentBet();
        }
        service.commitAndClose();
        System.out.println(sum == 800_000);
    }
}