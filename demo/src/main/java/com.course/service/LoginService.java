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
        //retrieve the student by email
        Optional<Student> optionalStudent=studentRepository.findByEmailId(email);
        //check if student exists
        if(optionalStudent.isPresent()){
            Student student=optionalStudent.get();
            //check if pass matches
            boolean passMatch= password.equals(student.getPassword());
            //check if the otp is verified
            boolean otpVerified= student.isOtpVerified();
            //if both conditions are met
            if(passMatch && otpVerified){
                return true;//login successfull
            }else{
                return false;//either pass does not match or otp not verified
            }
        }
        return false; //user not found
    }
}
