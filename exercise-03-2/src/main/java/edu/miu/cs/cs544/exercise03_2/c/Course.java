package edu.miu.cs.cs544.exercise03_2.c;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private int courseNumber;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "courses")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return Collections.unmodifiableList(this.students);
    }
}
