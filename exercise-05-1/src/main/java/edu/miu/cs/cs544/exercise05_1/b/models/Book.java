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
public class Book extends Product {
    private String title;

    public Book(long id, String name, String description, String title) {
        super(id, name, description);
        this.title = title;
    }
}
