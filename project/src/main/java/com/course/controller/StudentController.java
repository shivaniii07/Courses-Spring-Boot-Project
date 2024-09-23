package com.course.controller;

import com.course.model.Student;
import com.course.service.OtpService;
import com.course.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private OtpService otpService;

    //save the data in the database coming from the client as the request
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }
    //send-otp request
    @PostMapping("/send-otp")
    public String sendOtp(String email){
      String otp=otpService.generateOtp();
      otpService.sendOtp(email,otp);
      otpService.storeOtp(email,otp);
      return "OTP sent to"+email;
    }
    //validate otp request
    public String validateOtp(String email,String otp){
        if(otpService.validOtp(email,otp)){
            return "OTP is valid";
        }else{
            return "Invalid or expired OTP";
        }
    }


}
