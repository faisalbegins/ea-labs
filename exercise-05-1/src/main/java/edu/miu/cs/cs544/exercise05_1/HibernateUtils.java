package edu.miu.cs.cs544.exercise05_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void withTx(Consumer<Session> consumer) {
        Transaction tx = null;
        try(Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // user code
            System.out.println("executing user codes");
            consumer.accept(session);
            System.out.println("end executing user codes");

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }
    }
}
