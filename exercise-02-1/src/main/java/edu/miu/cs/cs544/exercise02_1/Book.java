package edu.miu.cs.cs544.exercise02_1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String ISBN;
    private String author;
    private double price;

    @Temporal(TemporalType.DATE)
    private Date publishDate;

    public Book(String title, String ISBN, String author, double price, Date publishDate) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
    }
}