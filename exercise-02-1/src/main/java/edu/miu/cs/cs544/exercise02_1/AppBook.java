package edu.miu.cs.cs544.exercise02_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AppBook {

	private static final SessionFactory sessionFactory;

    static {
		sessionFactory = HibernateUtils.getSessionFactory(Collections.singletonList(Book.class));
	}

    public static void main(String[] args) {
        Transaction tx = null;

        /*
         * Open Session
         * Create 3 Books and save them to database
         * Close session
         */
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // book 1
            Book book1 = new Book("Spring in Action", "ISBN-1", "Faisal Ahmed", 50.0, new Date());
            session.persist(book1);

            // book 2
            Book book2 = new Book("Hibernate in Action", "ISBN-2", "Muztafizur Rahman", 30.5, new Date());
            session.persist(book2);

            // book 3
            Book book3 = new Book("Hadoop in Action", "ISBN-3", "Rimon Muztafiz", 44.4, new Date());
            session.persist(book3);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }

        /*
         * Open Session
         * Retrieve all books and output them to the console
         * Close session
         */
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            List<Book> books = session.createQuery("from Book", Book.class).list();
            books.forEach(System.out::println);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }


        /*
         * Open Session
         * Retrieve a book from the database and change its title and price
         * Delete a book (not the one that was just updated)
         * Close session
         */
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Book book1 = session.load(Book.class, 1);
            book1.setTitle("Spring in Action 2");
            book1.setPrice(45.6);
            session.update(book1);

            Book book2 = session.load(Book.class, 2);
            session.delete(book2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }


        /*
         * Open Session
         * Retrieve all books and output them to the console
         * Close session
         */
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            List<Book> books = session.createQuery("from Book", Book.class).list();
            books.forEach(System.out::println);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
