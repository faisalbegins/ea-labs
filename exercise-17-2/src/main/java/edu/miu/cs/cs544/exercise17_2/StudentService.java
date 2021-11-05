package edu.miu.cs.cs544.exercise17_2;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initializeStudent() {
        studentDAO.initialize();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student getStudent(long studentId) {
        return studentDAO.load(studentId);
    }
}
