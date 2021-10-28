package edu.miu.cs.cs544.exercise03_2.e;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Reservation {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;
}
