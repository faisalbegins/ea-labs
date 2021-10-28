package edu.miu.cs.cs544.exercise03_1;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Owner {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @NonNull private String name;
    @NonNull private String address;
}
