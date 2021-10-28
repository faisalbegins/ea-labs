package edu.miu.cs.cs544.exercise03_2.f;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Department {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @NonNull private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private Collection<Employee> employees = new ArrayList<>();

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }
}
