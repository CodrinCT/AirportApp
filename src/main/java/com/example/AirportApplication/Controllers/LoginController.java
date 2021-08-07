package com.example.AirportApplication.Controllers;

import com.example.AirportApplication.Services.FlightsService;
import com.example.AirportApplication.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    FlightsService flightsService;

//    @Autowired
//    LoginService loginService;

    @Autowired
    RegistrationService registrationService;

  //  ApplicationUserRole applicationUserRole;

    @RequestMapping("/MyFirstWebApp/home")
    public String displayLogin(){
        return "index";
    }

    @PostMapping("/loginFail")
    public ModelAndView failureLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("loginFail","Login action has failed, please check your credentials and try again");
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/home");
    }
}
