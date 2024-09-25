package com.course.service;

import com.course.model.Student;
import com.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    //save student in database
    public Student saveStudent(Student student){
        if (studentRepository.findByEmailId(student.getEmailId()).isPresent()) {
          throw new IllegalArgumentException("Email is already in use");
       }
            return studentRepository.save(student);

    }
}
