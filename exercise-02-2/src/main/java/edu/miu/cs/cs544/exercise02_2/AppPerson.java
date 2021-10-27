package edu.miu.cs.cs544.exercise02_2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class AppPerson {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public static void main(String[] args) {
        Transaction tx = null;

        /*
         * Create / save 3 person objects
         * Retrieve all person objects from the database and print them to the console
         * Update a person, and delete another
         * Retrieve all person objects from the database and print them to the console
         */
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // Create / save 3 person objects
            Person person1 = new Person("John", "Wick", new Date());
            session.persist(person1);

            Person person2 = new Person("Alex", "Josh", new Date());
            session.persist(person2);

            Person person3 = new Person("Faisal", "Ahmed", new Date());
            session.persist(person3);

            // Retrieve all person objects from the database and print them to the console
            List<Person> people = session.createQuery("from Person", Person.class).list();
            people.forEach(System.out::println);


            // Update a person, and delete another
            person1 = session.load(Person.class, 1L);
            person1.setFirstName("Oliver");
            session.update(person1);

            person2 = session.load(Person.class, 2L);
            session.delete(person2);

            // Retrieve all person objects from the database and print them to the console
            people = session.createQuery("from Person", Person.class).list();
            people.forEach(System.out::println);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }
    }
}
