package edu.miu.cs.cs544.exercise03_2.b;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Publisher {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @NonNull
    private String name;
}
