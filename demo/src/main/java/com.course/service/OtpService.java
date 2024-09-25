package com.course.service;

import com.course.model.Otp;
import com.course.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    OtpRepository otpRepository;

    //to generate random number
    private final Random random=new Random();
    //generate random integer
    public String generateOtp(){
        return String.format("%06d",random.nextInt(1000000));//generate a 6-digit otp
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

    public void storeOtp(String email,String otp){
        LocalDateTime expiryTime=LocalDateTime.now().plusMinutes(5);//otp valid for 5 mins
        Otp otpEntity = new Otp(email,otp,expiryTime);
        otpRepository.save(otpEntity);
    }

    public boolean validOtp(String email,String otp){
        return otpRepository.findByEmail(email).map(storedOtp->{
            boolean isValid = storedOtp.getOtp().equals(otp) && LocalDateTime.now().isBefore(storedOtp.getExpiryTime());
            if(!isValid){
//                //optionally removed expired otp
                otpRepository.deleteByEmail(email);
            }
            return isValid;
        })
        .orElse(false);
    }

}
