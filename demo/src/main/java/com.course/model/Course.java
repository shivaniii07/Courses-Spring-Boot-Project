package com.course.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="courses_shivani")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String duration;

    @ElementCollection
    private List<String>tags;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(nullable = false)
    private double fee;

    @Column(name = "students_enroll")
    private int studentsEnrolled;

    //getter setter
    public int getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(int studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }



    //constructor
    public Course(String courseName, String description, String duration,
                  List<String> tags, String teacherName, double fee,
                  int studentsEnrolled) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.tags = tags;
        this.teacherName = teacherName;
        this.fee = fee;
        this.studentsEnrolled = studentsEnrolled;
    }
    public Course(){}


}
