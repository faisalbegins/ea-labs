package edu.miu.cs.cs544.exercise03_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class App {

	private static final SessionFactory sessionFactory;

    static {
		sessionFactory = HibernateUtils.getSessionFactory();
	}

    public static void main(String[] args) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // crate car 1 and add owner
            Car chrysler = Car.of("Chrysler", "Pacifica2021", 58000);
            chrysler.setOwner(Owner.of("Faisal", "FairField"));
            session.persist(chrysler);

            // crate car 2 and add owner
            Car dodge = Car.of("Dodge Durango", "SRT2021", 66000);
            dodge.setOwner(Owner.of("Ahmed", "Davenport"));
            session.persist(dodge);

            // get all the cars
            List<Car> cars = session.createQuery("from Car", Car.class).list();
            cars.forEach(System.out::println);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }
        System.exit(0);
    }
}
