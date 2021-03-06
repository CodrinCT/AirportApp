package com.example.AirportApplication.Controllers;
import com.example.AirportApplication.Models.User;
import com.example.AirportApplication.Services.RegistrationService;
import com.example.AirportApplication.Services.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/registration")
    public ModelAndView displayRegistrationPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registerTheUser(User user, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
            if (!registrationService.getUserEmail().contains(user.getEmail()) && !registrationService.getUserName().contains(user.getUsername())){
                String siteURL = Utility.getSiteUrl(request);
                registrationService.registerUser(user);
                try {
                    registrationService.sendVerificationEmail(user, siteURL);
                } catch (UnsupportedEncodingException | MessagingException e) {
                    e.printStackTrace();
                }
                mav.addObject("success", "You have successfully registered, please look in your email to verify your account");
                mav.setViewName("register");
            }else if (registrationService.getUserName().contains(user.getUsername())){

                mav.addObject("error", "Sorry, but this name is already associated with another account, please try again");
                mav.setViewName("register");
            }
            else
            {
                mav.addObject("error", "User Name or Password are wrong");
                mav.setViewName("register");
            }
       return mav;
    }

    @GetMapping("/verify")
    public ModelAndView verifyAccount(@Param(value = "code") String code){
        ModelAndView mav = new ModelAndView();
        boolean verified = registrationService.verifyCode(code);
        if (verified){
            mav.addObject("success", "You have succeeded in confirming your account thank you for your cooperation ");
        }
        else
        {
            mav.addObject("failure", "We are sorry but it seems that either your verification code is wrong or has expired, please try again");
        }
        mav.setViewName("verify");
        return mav;
    }
}
