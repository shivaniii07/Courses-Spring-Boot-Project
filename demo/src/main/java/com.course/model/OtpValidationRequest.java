package com.course.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public class OtpValidationRequest {
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "otp is mandatory")
    private String otp;

    //constructor
    public OtpValidationRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }
    public OtpValidationRequest(){}


    //getter setter
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
