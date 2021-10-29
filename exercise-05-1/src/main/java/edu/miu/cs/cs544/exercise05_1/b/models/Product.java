package edu.miu.cs.cs544.exercise05_1.b.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @NonNull
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;
}
