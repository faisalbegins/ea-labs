package edu.miu.cs.cs544.exercise03_2.e;

import java.time.LocalDate;
import java.util.List;

import static edu.miu.cs.cs544.exercise03_2.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
            Customer c1 = Customer.of("Faisal");
            Reservation r1 = Reservation.of(LocalDate.now());
            r1.setBook(Book.of("ISBN1", "Spring", "Faisal"));
            Reservation r2 = Reservation.of(LocalDate.of(2021,11,1));
            r2.setBook(Book.of("ISBN2", "Hadoop", "Ahmed"));
            c1.addReservation(r1);
            c1.addReservation(r2);
            session.persist(c1);
        });

        withTx(session -> {
            List<Customer> customers = session.createQuery("from Customer", edu.miu.cs.cs544.exercise03_2.e.Customer.class).list();
            customers.forEach(System.out::println);
        });

        System.exit(0);
    }
}
