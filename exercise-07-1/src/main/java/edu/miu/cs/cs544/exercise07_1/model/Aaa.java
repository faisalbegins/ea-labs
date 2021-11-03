package edu.miu.cs.cs544.exercise07_1.model;

import javax.persistence.*;

@Entity
public class Aaa {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToOne
    @JoinTable(
            name = "aaa_bbb_one2one",
            joinColumns = {@JoinColumn(name = "a_id")},
            inverseJoinColumns = {@JoinColumn(name = "b_id")}
    )
    private Bbb bbb;
}
