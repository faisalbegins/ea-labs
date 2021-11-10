package edu.miu.cs.cs544.springrest.repository;

import edu.miu.cs.cs544.springrest.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> { }
