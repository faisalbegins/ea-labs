package edu.miu.cs.cs544.exercise03_2.b;

import java.util.List;

import static edu.miu.cs.cs544.exercise03_2.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
            Book spring = Book.of("ISBN1", "Spring", "Faisal");
            spring.setPublisher(Publisher.of("Packt"));
            session.persist(spring);

            Publisher p = session.get(Publisher.class, 1L);

            Book hadoop = Book.of("ISBN2", "Hadoop", "Ahmed");
            hadoop.setPublisher(p);
            session.persist(hadoop);

            Book hibernate = Book.of("ISBN3", "Hibernate", "John");
            session.persist(hibernate);
        });

        withTx(session -> {
            List<Book> books = session.createQuery("from Book", Book.class).list();
            books.forEach(System.out::println);

            List<Publisher> publishers = session.createQuery("from Publisher ", Publisher.class).list();
            publishers.forEach(System.out::println);
        });

        System.exit(0);
    }
}
