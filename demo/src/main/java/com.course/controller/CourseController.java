package com.course.controller;

import com.course.model.Course;
import com.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //create course
    @PostMapping("/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course savedCourse=courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }
    //get all course
    @GetMapping("/getAllCourse")
    public List<Course> getAllCouse(){
        return courseService.getAllCourses();
    }

    //get course by name
    @GetMapping("/courseName")
    public List<Course> getByName(@RequestParam String courseName){
        return courseService.getCourseByName(courseName);
    }
    @GetMapping("/parameters")
    public List<Course> getByParameters(@RequestParam Map<String,String> parameters){
        return courseService.getByParameter(parameters);
    }
    //get couse by id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
      Course course=courseService.getCourseById(id);
      if(course!=null){
          return ResponseEntity.ok(course);
      }
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    //enrolled student
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<?> enrollStudent(
            @PathVariable Long courseId, @RequestParam String studentEmail
    ){
        try{
            courseService.enrollStudent(studentEmail,courseId);
            return ResponseEntity.ok("Successfully enrolled in the course");
        }catch (IllegalArgumentException ex){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
