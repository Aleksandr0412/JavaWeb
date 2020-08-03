package com.aleksandr0412.hibernate2.locks;

import com.aleksandr0412.hibernate2.entities.User;
import com.aleksandr0412.hibernate2.service.Service;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class PessimisticLock extends Thread {
    private final User user;
    private final CountDownLatch cdl;
    private final SessionFactory sessionFactory;

    public PessimisticLock(User user, CountDownLatch cdl, SessionFactory sessionFactory) {
        this.user = user;
        this.cdl = cdl;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        incRandomLotBet(100);
        cdl.countDown();
    }

    public void incRandomLotBet(long bet) {
        Random r = new Random();
        Service service = new Service(sessionFactory);
        try {
            for (int i = 0; i < 1000; i++) {
                service.startSession();
                Long userId = user.getId();
                Long lotId = (long) r.nextInt(4) + 1;
                service.incBetPessimistic(userId, lotId, bet);
                service.commit();
                sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.endSession();
        }
    }
}