package edu.miu.cs.cs544.springrest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {
    @Id
    @NonNull
    private long studentId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @OneToMany
    @JoinColumn(name = "student_id")
    private final Collection<Course> courses = new ArrayList<Course>();

    public Collection<Course> getCourses() {
        return Collections.unmodifiableList(new ArrayList<>(this.courses));
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
