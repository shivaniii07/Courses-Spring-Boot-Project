package com.course.service;

import com.course.model.Course;
import com.course.model.Enrollment;
import com.course.model.Student;
import com.course.repository.CourseRepository;
import com.course.repository.EnrollmentRepository;
import com.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;

    //to save course
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
    //get all course
    public List<Course> getAllCourses(){
      return courseRepository.findAll();
    }
    //get course by id
    public Course getCourseById(Long id){
        return courseRepository.findById(id).orElse(null);
    }

    //enroll the student
    // save the name and email of the student
    //and increment the student erolled by 1

    public void enrollStudent(String studentEmail,Long courseId){

        //check if student is registered
        Optional<Student>optionalStudent=studentRepository.findByEmailId(studentEmail);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("Student not registered");
        }
        //get registered student
        Student student=optionalStudent.get();
        //find the course
        Course course=courseRepository.findById(courseId).orElseThrow(()->
            new IllegalArgumentException("Course not found with id:"+courseId));

        //create a new enrollment entry
        Enrollment enrollment=new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        //save the enrollment
        enrollmentRepository.save(enrollment);

        //increment the number of enrolled student
        course.setStudentsEnrolled(course.getStudentsEnrolled()+1);
        courseRepository.save(course);

    }

}
