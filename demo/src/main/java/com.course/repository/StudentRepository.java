package com.course.repository;

import com.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
 Optional<Student> findByEmailId(String emailId);
 boolean existsByRollNo(String rollNo);
}
