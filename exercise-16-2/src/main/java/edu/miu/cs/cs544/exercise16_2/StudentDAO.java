package edu.miu.cs.cs544.exercise16_2;

import org.hibernate.SessionFactory;

public class StudentDAO {
    private final SessionFactory sf = HibernateUtils.getSessionFactory();

    public StudentDAO() {
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
