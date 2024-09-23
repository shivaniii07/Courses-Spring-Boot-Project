package com.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender emailSender;

    //to generate random number
    private final Random random=new Random();
    //generate random integer
    public String generateOtp(){
        return String.format("%06d",random.nextInt(1000000));
    }
    //to send otp to required email
    public void sendOtp(String email, String otp){
        SimpleMailMessage mesaage=new SimpleMailMessage();
        mesaage.setTo(email);
        mesaage.setSubject("Your OTP Code");
        mesaage.setText("Your OTP Code is:"+otp);
        emailSender.send(mesaage);
    }

    //otp storage and validation
    private Map<String,String > otpStorage=new HashMap<>(); //storing the otp
    private Map<String ,Long>otpExpiry=new HashMap<>(); //storing expiring time

    public void storeOtp(String email,String otp){
       otpStorage.put(email,otp);
       otpExpiry.put(email,System.currentTimeMillis()+300000); //valid for 5 mins.. 5*60*1000
    }

    public boolean validOtp(String email,String otp){
        if(otpExpiry.containsKey(email) && otpExpiry.get(email)> System.currentTimeMillis()){
            return otp.equals(otpStorage.get(email));
        }
        return false;
    }
}
