package com.example.AirportApplication.Controllers;

import com.example.AirportApplication.Services.PassengersService;
import com.example.AirportApplication.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PassengersController {

    @Autowired
    PassengersService passengersService;

    @Autowired
    TicketService ticketService;

    @GetMapping("/passengers/{id}")
    public ModelAndView displayFlightPassengers(@PathVariable int id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("flight-passengers");
        mav.addObject("passenger", passengersService.getPassengersByFlightId(id));
        return mav;
    }
    @PostMapping("/cancel/{ticketId}")
    public ModelAndView cancelPassenger(Authentication authentication, @PathVariable("ticketId") int ticketId){
        ModelAndView mav = new ModelAndView();
        passengersService.cancelPassenger(ticketId);
        ticketService.cancelTicket(authentication.getName());
        mav.setViewName("redirect:/flightslist");
        return mav;
    }
}
