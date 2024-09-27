package com.course.model;

import jakarta.persistence.*;

@Entity
@Table(name="enroll_shivani")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String studentName;
//
//    @Column(name="student_email",nullable = false)
//    private String studentEmail;

    @ManyToOne
    @JoinColumn(name="course_id",nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_roll_no", nullable = false)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public Enrollment() {

    }

    //getter setter
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getStudentEmail() {
//        return studentEmail;
//    }
//
//    public void setStudentEmail(String studentEmail) {
//        this.studentEmail = studentEmail;
//    }


    //constructor
    public Enrollment(Course course,Student student) {
        this.course = course;
        this.student=student;
    }


}
