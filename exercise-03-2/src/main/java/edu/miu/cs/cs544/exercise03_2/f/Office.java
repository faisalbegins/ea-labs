package edu.miu.cs.cs544.exercise03_2.f;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Office {
    @Id @GeneratedValue
    private long id;

    @NonNull
    private int roomNumber;

    @NonNull
    private String building;

    @OneToMany(mappedBy = "office")
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();
}
