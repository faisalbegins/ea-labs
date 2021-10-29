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
public class CD extends Product {
    private String artist;

    public CD(long id, String name, String description, String artist) {
        super(id, name, description);
        this.artist = artist;
    }
}
