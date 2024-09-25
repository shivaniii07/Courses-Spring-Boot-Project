package com.course.service;

import com.course.model.Student;
import com.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    StudentRepository studentRepository;

    public boolean validateLogin(String email,String password){
        Optional<Student> optionalStudent=studentRepository.findByEmailId(email);
        if(optionalStudent.isPresent()){
            Student student=optionalStudent.get();
            return password.equals(student.getPassword());
        }
        return false;
    }
}
