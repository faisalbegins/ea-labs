package edu.miu.cs.cs544.exercise03_2.f;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of = {"employeeNumber", "name", "office"})
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Employee {
    @Id @NonNull
    private String employeeNumber;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "office_id")
    private Office office;
}
