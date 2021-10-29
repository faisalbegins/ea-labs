package edu.miu.cs.cs544.exercise05_1.a.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Product {
    @Id
    @NonNull
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;
}
