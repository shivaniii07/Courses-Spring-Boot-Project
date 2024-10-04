package com.course.controller;

import com.course.exception.StudentNotFoundException;
import com.course.model.Course;
import com.course.model.LoginRequest;
import com.course.model.OtpValidationRequest;
import com.course.model.Student;
import com.course.repository.StudentRepository;
import com.course.service.LoginService;
import com.course.service.OtpService;
import com.course.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private LoginService loginService;
    @Autowired
    StudentRepository studentRepository;


    //save the data in the database coming from the client as the request
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody Student student){
        try{
            //save the data in the database
            studentService.saveStudent(student);

            //generate otp and send it to the registered email
            String otp=otpService.generateOtp();
            otpService.sendOtp(student.getEmailId(),otp);
            otpService.storeOtp(student.getEmailId(),otp);

            return ResponseEntity.ok("Student registered successfully." +
                    "OTP send to"+" "+student.getEmailId());
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    //validate otp request
//    @PostMapping("/validate")
//    public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest otpValidationRequest){
//        try{
//            boolean isValid= otpService.validOtp(otpValidationRequest.getEmail(),otpValidationRequest.getOtp());
//            return ResponseEntity.ok(isValid?"OTP is Valid":"Invalid or expired OTP");
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while validating OTP.");
//        }
//    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest request){
        Optional<Student>optionalStudent=studentRepository.findByEmailId(request.getEmail());
        if(optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            //validate otp
            if (otpService.validOtp(request.getEmail(), request.getOtp())) {
                student.setOtpVerified(true);
                studentRepository.save(student);
                return ResponseEntity.ok("valid OTP");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while validating OTP.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    //login with email and pass
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody LoginRequest request){
        boolean isValid=loginService.validateLogin(request.getEmail(), request.getPassword());
        if(isValid){
            return ResponseEntity.ok("Login successfull");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP not verified");
        }
    }

    //get courses by student id
    @GetMapping("/{studentEmail}/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable String studentEmail) throws StudentNotFoundException {
        List<Course>courses=studentService.getCoursesByStudentEmail(studentEmail);
        if(courses==null || courses.isEmpty()){
            throw new StudentNotFoundException("Student not found with the email:"+studentEmail);
            //ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found");


        }
        return ResponseEntity.ok(courses);
    }
}
