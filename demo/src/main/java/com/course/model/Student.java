package com.course.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="student_shivani")
public class Student {

    @Id
    @Column(unique = true,nullable = false,name="rollNo")
    @NotBlank(message = "Roll number is mandatory")
    private String rollNo;

    @Column(name="firstName")
    @NotBlank(message = "First name is mandatory")
    private String firstName;


    @Column(name="lastName")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;


    @Column(name="emailId")
    @NotBlank(message ="Email id is mandatory")
    private String emailId;


    @Column(name="mobile")
    @NotBlank(message = "mobile number is mandatory")
    @Size(min=10,max=10,message = "Enter the correct phone number")
    private String mobile;


    @Column(name="password")
    @NotBlank(message = "Password is mandatory")
    @Size(min=6,message="Password should be of length 6")
    private String password;


    @Column(name="confirmPass")
    @NotBlank(message = "confirm password is mandatory")
    private String confirmPassword;


    @Embedded
    @Column(name="Address")
    @Valid
    private Address address;

    // getter ,setter ,constructor
    public Student(String rollNo, String firstName, String lastName,
                   String emailId, String mobile, String password,
                   String confirmPassword, Address address) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.mobile = mobile;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
    }

    public Student(){}

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
