package edu.miu.cs.cs544.exercise05_1.a;

import edu.miu.cs.cs544.exercise05_1.a.models.Customer;
import edu.miu.cs.cs544.exercise05_1.a.models.Order;
import edu.miu.cs.cs544.exercise05_1.a.models.OrderLine;
import edu.miu.cs.cs544.exercise05_1.a.models.Product;

import java.time.LocalDate;

import static edu.miu.cs.cs544.exercise05_1.HibernateUtils.getSessionFactory;
import static edu.miu.cs.cs544.exercise05_1.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        // creating 4 products
        withTx(session -> {
            Product p1 = Product.of(1L, "P1", "P1 Description");
            Product p2 = Product.of(2L, "P2", "P2 Description");
            Product p3 = Product.of(3L, "P3", "P3 Description");
            Product p4 = Product.of(4L, "P4", "P4 Description");
            session.persist(p1);
            session.persist(p2);
            session.persist(p3);
            session.persist(p4);
        });

        // create customer
        withTx(session -> {
            session.persist(Customer.of(1L, "Faisal", "Ahmed"));
            session.persist(Customer.of(2L, "John", "Wick"));
        });

        withTx(session -> {
            Customer faisal = session.load(Customer.class, 1L);

            // create 1st order for faisal
            Order order1 = Order.of(1L, LocalDate.now());
            order1.setCustomer(faisal);
            order1.addOrderLine(OrderLine.of(1L, 10, session.load(Product.class, 1L)));
            order1.addOrderLine(OrderLine.of(2L, 20, session.load(Product.class, 2L)));


            // create 2nd order for faisal and saving it in different approach
            Order order2 = Order.of(2L, LocalDate.now());
            order2.setCustomer(faisal);
            order2.addOrderLine(OrderLine.of(3L, 30, session.load(Product.class, 3L)));
            order2.addOrderLine(OrderLine.of(4L, 40, session.load(Product.class, 4L)));

            // save orders
            session.persist(order1);
            session.persist(order2);
        });

        // show data
        withTx(session -> {
            Customer customer = session.load(Customer.class, 1L);
            System.out.println(customer);
        });

        getSessionFactory().close();
        System.exit(0);
    }
}
