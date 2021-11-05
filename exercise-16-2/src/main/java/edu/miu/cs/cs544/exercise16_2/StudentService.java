package edu.miu.cs.cs544.exercise16_2;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    public Student getStudent(long studentId) {
        return studentDAO.load(studentId);
    }
}
