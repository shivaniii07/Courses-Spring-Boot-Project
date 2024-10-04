package com.course.repository;

import com.course.model.Course;
import com.course.model.Enrollment;
import com.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
  List<Enrollment> findByStudent(Student student);
  Optional<Enrollment>findByStudentAndCourse(Student student, Course course);
}
