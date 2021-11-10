package edu.miu.cs.cs544.springrest.service;

import edu.miu.cs.cs544.springrest.domain.Course;
import edu.miu.cs.cs544.springrest.domain.Student;
import edu.miu.cs.cs544.springrest.repository.CourseRepository;
import edu.miu.cs.cs544.springrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public RegistrationServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void initialize() {
        Course course1 = new Course(1101, "Java", "A");
        Course course2 = new Course(1102, "Math", "B-");
        courseRepository.save(course1);
        courseRepository.save(course2);

        Student student = new Student(11334, "Frank", "Brown");
        student.addCourse(course1);
        student.addCourse(course2);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
