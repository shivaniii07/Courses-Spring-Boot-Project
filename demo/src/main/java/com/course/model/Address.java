package com.course.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable //used this becoz i want to embed address in the student class
public class Address {


    private String city;
    private String state;
    @NotBlank(message ="pincode is mandatory")
    @Size(min=5,max=6,message = "Enter the correct pincode")
    private String pincode;

    //constructor
    public Address(String city, String state, String pincode) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Address(){}

    //getter and setter
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


}

