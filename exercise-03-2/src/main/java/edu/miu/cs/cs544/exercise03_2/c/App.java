package edu.miu.cs.cs544.exercise03_2.c;

import java.util.List;

import static edu.miu.cs.cs544.exercise03_2.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
            Course fpp = Course.of(390, "FPP");
            session.persist(fpp);

            Student s1 = Student.of(1, "F", "A");
            s1.addCourse(fpp);
            s1.addCourse(Course.of(544, "EA"));

            session.persist(s1);
        });

        withTx(session -> {
            System.out.println("all courses");
            List<Course> courses = session.createQuery("from Course", Course.class).list();
            courses.forEach(System.out::println);

            System.out.println("all student with their courses");
            List<Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(System.out::println);
        });
    }
}
