package edu.miu.cs.cs544.exercise07_1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bbb {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToOne(mappedBy = "bbb")
    private Aaa aaa;
}
