package edu.miu.cs.cs544.exercise03_2.d;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "customer_reservations",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    @Setter(AccessLevel.PRIVATE)
    private List<Reservation> reservations = new ArrayList<>();

    public boolean addReservation(Reservation reservation) {
        return reservations.add(reservation);
    }

    public boolean removeReservation(Reservation reservation) {
        return reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(this.reservations);
    }
}
