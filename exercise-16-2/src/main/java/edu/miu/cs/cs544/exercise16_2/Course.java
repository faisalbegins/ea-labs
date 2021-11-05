package edu.miu.cs.cs544.exercise16_2;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    private long courseNumber;

    private String name;
    private String grade;

    public Course(long courseNumber, String name, String grade) {
        this.courseNumber = courseNumber;
        this.name = name;
        this.grade = grade;
    }

    public Course() {
    }

    public long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
