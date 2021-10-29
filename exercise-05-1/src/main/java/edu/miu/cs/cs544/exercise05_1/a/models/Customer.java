package edu.miu.cs.cs544.exercise05_1.a.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Customer {
    @Id
    @NonNull
    private long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private Collection<Order> orders = new ArrayList<>();

    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    public Collection<Order> getOrders() {
        return Collections.unmodifiableList(new ArrayList<>(this.orders));
    }
}
