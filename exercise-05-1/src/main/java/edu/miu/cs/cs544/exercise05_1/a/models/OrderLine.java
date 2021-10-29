package edu.miu.cs.cs544.exercise05_1.a.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class OrderLine {
    @Id
    @NonNull
    private long id;

    @NonNull
    private int quantity;

    @ManyToOne
    @NonNull
    private Product product;
}
