package edu.miu.cs.cs544.exercise03_2.a;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Employee {
    @Id @NonNull
    private String employeeNumber;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    private Department department;
}
