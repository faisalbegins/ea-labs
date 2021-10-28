package edu.miu.cs.cs544.exercise03_2.d;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
