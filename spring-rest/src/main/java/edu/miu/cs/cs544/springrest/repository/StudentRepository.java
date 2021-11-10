package edu.miu.cs.cs544.springrest.repository;

import edu.miu.cs.cs544.springrest.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { }
