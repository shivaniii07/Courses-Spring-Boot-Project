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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    //get course by course name
    public List<Course> getCourseByName(String courseName){
        List<Course> courses=getAllCourses();
        return courses.stream().filter(course ->course.getCourseName().equalsIgnoreCase(courseName))
                .collect(Collectors.toList());
    }
    //get courses by different parameters
    public List<Course> getByParameter(Map<String ,String> mapOfParameter){
        List<Course> courses=getAllCourses();
        for(String key: mapOfParameter.keySet()){
            courses=courses.stream().filter(course -> getField(key,course).equalsIgnoreCase(mapOfParameter.get(key)))
                    .collect(Collectors.toList());
        }
        return courses;
    }
    public String getField(String key,Course course){
        switch (key){
            case "courseName":
                return course.getCourseName();
            case "duration":
                return course.getDuration();
            case "teacherName":
                return course.getTeacherName();
            default:
            return "";
        }
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

         //check if student is already enrolled in the course
        Optional<Enrollment>existingEnrollment=enrollmentRepository.findByStudentAndCourse(student,course);
        if(existingEnrollment.isPresent()){
            throw new IllegalArgumentException("Student is already enrolled in the course");
        }
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
