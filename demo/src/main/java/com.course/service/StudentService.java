package com.course.service;

import com.course.exception.StudentNotFoundException;
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
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    //save student in database
    public Student saveStudent(Student student){
        //email duplicacy
        if (studentRepository.findByEmailId(student.getEmailId()).isPresent()) {
          throw new IllegalArgumentException("Email is already in use");
       }
        //roll number duplicacy
        if(studentRepository.existsByRollNo(student.getRollNo())){
            throw new IllegalArgumentException("Roll number is already in use");
        }
            return studentRepository.save(student);

    }
    //get courses by student id
    public List getCoursesByStudentEmail(String studentEmail) throws StudentNotFoundException {
        Student student=studentRepository.findByEmailId(studentEmail)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));

        List<Enrollment> enrollments=enrollmentRepository.findByStudent(student);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }
}
