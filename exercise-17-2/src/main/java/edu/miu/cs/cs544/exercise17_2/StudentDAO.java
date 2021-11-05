package edu.miu.cs.cs544.exercise17_2;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.MANDATORY)
public class StudentDAO {
    private SessionFactory sf;

    @Transactional(propagation=Propagation.SUPPORTS)
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public void initialize() {
        Student student = new Student(11334, "Frank", "Brown");
        Course course1 = new Course(1101, "Java", "A");
        Course course2 = new Course(1102, "Math", "B-");
        student.addCourse(course1);
        student.addCourse(course2);
        sf.getCurrentSession().persist(student);
    }

    public Student load(long studentId) {
        return sf.getCurrentSession().load(Student.class, studentId);
    }
}
