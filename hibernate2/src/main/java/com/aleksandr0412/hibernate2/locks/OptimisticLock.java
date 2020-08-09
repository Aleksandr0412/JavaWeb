package com.aleksandr0412.hibernate2.locks;

import com.aleksandr0412.hibernate2.entities.User;
import com.aleksandr0412.hibernate2.service.Service;
import org.hibernate.SessionFactory;

import javax.persistence.OptimisticLockException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class OptimisticLock extends Thread {
    private SessionFactory sessionFactory;
    private final User user;
    private final CountDownLatch cdl;

    public OptimisticLock(User user, CountDownLatch cdl, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.user = user;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        incRandomLotBet(100);
        cdl.countDown();
    }

    public void incRandomLotBet(long bet) {
        Random r = new Random();
        Service service = new Service(sessionFactory);
        service.startSession();
        try {
            for (int i = 0; i < 1000; i++) {
                Long userId = user.getId();
                Long lotId = (long) r.nextInt(4) + 1;
                service.incBet(userId, lotId, bet);
                Thread.sleep(1);
            }
            service.commit();
        } catch (OptimisticLockException e) {
            service.rollBack();
            incRandomLotBet(bet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.endSession();
        }
    }
}