package edu.miu.cs.cs544.springrest.service;

import edu.miu.cs.cs544.springrest.domain.Student;

import java.util.List;

public interface RegistrationService {
    void initialize();
    List<Student> findAll();
}
