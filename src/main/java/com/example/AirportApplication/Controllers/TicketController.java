package com.example.AirportApplication.Controllers;

import com.example.AirportApplication.Models.TicketModel;
import com.example.AirportApplication.Models.User;
import com.example.AirportApplication.Services.FlightsService;
import com.example.AirportApplication.Services.PassengersService;
import com.example.AirportApplication.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    PassengersService passengersService;

    @Autowired
    FlightsService flightsService;

    @GetMapping("/flightslist/tickets/{id}")
    public ModelAndView showTicketOptions(@PathVariable("id") int id){
       ModelAndView mav = new ModelAndView();
       mav.setViewName("buy_ticket");
       mav.addObject("planeClass", ticketService.getPlaneClass());
       mav.addObject("flight", id);
       return mav;
    }

    @PostMapping("/flightslist/tickets")
    public ModelAndView buyTicket(Authentication authentication, @RequestParam String className, @RequestParam int flightId){
        ModelAndView mav = new ModelAndView();
        int freeSeats = flightsService.getAvailableNumberOfSits(flightId);
        if (freeSeats >=1){
            ticketService.makeTicket(flightId, Integer.parseInt(ticketService.getPriceByName(className)), authentication.getName());
            TicketModel ticketModel = ticketService.getTicketByTicketId(authentication.getName());
            passengersService.registerPassengerToFlight(authentication.getName(), ticketModel.getTicketId(), flightId);
            passengersService.changeNumberOfAvailableSeats(flightId);
            mav.setViewName("buy_ticket");
            mav.addObject("successful_buy","The ticket has been bought successfully!");
        }else {
            mav.setViewName("buy_ticket");
            mav.addObject("no_free_sits_left", "Sorry, but there are no free seats left, for this flight." +
                    " We are sorry for the inconvenience.");
        }
       return mav;
    }

    @DeleteMapping("/flightslist/cancel/{id}")
    public ModelAndView cancelTicket(User user){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/flightslist");
        ticketService.cancelTicket(user.getUsername());
        return mav;
    }
}


