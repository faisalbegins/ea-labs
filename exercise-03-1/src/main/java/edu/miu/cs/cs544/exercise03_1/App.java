package edu.miu.cs.cs544.exercise03_1;

import java.util.List;

import static edu.miu.cs.cs544.exercise03_1.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
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
        });
        System.exit(0);
    }
}
