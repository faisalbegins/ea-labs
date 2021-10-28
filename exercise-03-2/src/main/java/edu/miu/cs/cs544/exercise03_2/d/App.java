package edu.miu.cs.cs544.exercise03_2.d;

import java.time.LocalDate;
import java.util.List;

import static edu.miu.cs.cs544.exercise03_2.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
            Customer c1 = Customer.of("Faisal");
            c1.addReservation(Reservation.of(LocalDate.now()));
            c1.addReservation(Reservation.of(LocalDate.of(2021,11,1)));
            session.persist(c1);
        });

        withTx(session -> {
            List<Customer> customers = session.createQuery("from edu.miu.cs.cs544.exercise03_2.d.Customer", Customer.class).list();
            customers.forEach(System.out::println);
        });

        System.exit(0);
    }
}
