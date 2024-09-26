package com.course.service;

import com.course.model.Enrollment;
import com.course.model.Student;
import com.course.repository.EnrollmentRepository;
import com.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    //save student in database
    public Student saveStudent(Student student){
        if (studentRepository.findByEmailId(student.getEmailId()).isPresent()) {
          throw new IllegalArgumentException("Email is already in use");
       }
            return studentRepository.save(student);

    }

    public long getEnrollmentCourse(String studentEmail){
        List<Enrollment> enrollments=enrollmentRepository.findByStudentEmail(studentEmail);
        return enrollments.size();
    }
}
