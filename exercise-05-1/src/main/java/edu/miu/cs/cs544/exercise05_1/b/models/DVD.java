package edu.miu.cs.cs544.exercise05_1.b.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class DVD extends Product {
    private String genre;

    public DVD(long id, String name, String description, String genre) {
        super(id, name, description);
        this.genre  = genre;
    }
}
