package edu.miu.cs.cs544.exercise05_1.a.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "`Order`")
@RequiredArgsConstructor(staticName = "of")
public class Order {
    @Id
    @NonNull
    private long orderId;

    @NonNull
    private LocalDate date;

    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Collection<OrderLine> orderLines = new ArrayList<>();

    public boolean addOrderLine(OrderLine orderLine) {
        return orderLines.add(orderLine);
    }

    public Collection<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(new ArrayList<>(this.orderLines));
    }
}
