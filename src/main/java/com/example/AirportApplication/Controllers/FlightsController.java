package com.example.AirportApplication.Controllers;

import com.example.AirportApplication.Models.FlightsModel;
import com.example.AirportApplication.Services.FlightsService;
import com.example.AirportApplication.Services.PassengersService;
import com.example.AirportApplication.Services.TicketService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/flightslist")
public class FlightsController {

    @Autowired
    FlightsService flightsService;

    @Autowired
    PassengersService passengersService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayFlightsUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list-user-flights");
        modelAndView.addObject("flights",flightsService.getAllFlights());
        return modelAndView;
    }

    @GetMapping("/information/{flightId}")
    public ModelAndView displayFlightInformation(@PathVariable("flightId") int flightId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plane-info-list");
        passengersService.changeNumberOfAvailableSeats(flightId);
        modelAndView.addObject("info", flightsService.getById(flightId));
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView displaySpecifiedFlights(FlightsModel flightsModel){
        ModelAndView modelAndView = new ModelAndView();
        if (!flightsModel.getOrigin().equals("")&& !flightsModel.getDestination().equals("")){
            modelAndView.setViewName("list-user-flights");
            modelAndView.addObject("flights" ,flightsService.showFlightsByOriginAndDestination(flightsModel.getOrigin(), flightsModel.getDestination()));
            System.out.println("both");
        }else if (!flightsModel.getOrigin().equals("") & flightsModel.getDestination().equals("")){
            modelAndView.setViewName("list-user-flights");
            modelAndView.addObject("flights" ,flightsService.showFlightsByOrigin(flightsModel.getOrigin()));
            System.out.println("origin");
        }else if (flightsModel.getOrigin().equals("") && !flightsModel.getDestination().equals("")){
            modelAndView.setViewName("list-user-flights");
            modelAndView.addObject("flights" ,flightsService.showFlightsByDestination(flightsModel.getDestination()));
            System.out.println("destination");
        }else {
            modelAndView.setViewName("redirect:/flightslist");
        }

        return modelAndView;
    }

    @GetMapping("/makeORremove")
    public ModelAndView displayPage(){
     ModelAndView mav = new ModelAndView();
     mav.setViewName("make_flight");
     return mav;
    }

    @PostMapping("/newFlight")
    public ModelAndView makeFlight( String origin, String destination,
                                    String timeOfDeparture, String timeOfArrival,
                                    String timeUntilArrival, String totalNrOfSits,
                                    String availableNrOfSits){
        ModelAndView mav = new ModelAndView();
        if(!origin.equals("")&&!destination.equals("")&&!timeOfDeparture.equals("")&&!timeOfArrival.equals("")&&!timeUntilArrival.equals("")
                &&totalNrOfSits.equals("") &&availableNrOfSits.equals("")){
            flightsService.makeFlight(origin,destination,timeOfDeparture,timeOfArrival,timeUntilArrival, Integer.parseInt(totalNrOfSits), Integer.parseInt(availableNrOfSits));
            mav.setViewName("make_flight");
            mav.addObject("made","Flight created successfully!");
        }else {
            mav.setViewName("make_flight");
            mav.addObject("failMake","Every input row must be completed!");
        }
        return mav;
    }

    @PostMapping("/deleteFlight")
    public ModelAndView deleteFlightByAdmin(@RequestParam int flightId){
        ModelAndView mav = new ModelAndView();
        if(flightsService.doesFlightExists(flightId) == 1){
        flightsService.deleteFlight(flightId);
        mav.setViewName("make_flight");
        mav.addObject("deleted","Flight deleted successfully!");
        }
        else {
            mav.setViewName("make_flight");
            mav.addObject("failDelete","There is no flight with this id!");
        }
        return mav;
    }

    @GetMapping("/myflights")
    public ModelAndView myFlights(@NotNull Authentication authentication){
        ModelAndView mav = new ModelAndView();
        try{
            mav.addObject("myFlights", flightsService.getMyFlights(authentication.getName()));
            mav.addObject("tickets",ticketService.getTicketByPassengerName(authentication.getName()));
        }catch (EmptyResultDataAccessException e){
            mav.setViewName("myFlights");
            return mav;
        }
        mav.setViewName("myFlights");
        return mav;
    }
    @PostMapping("/cancelMyFlight")
    public ModelAndView cancelFlight(Authentication authentication, @RequestParam int ticketId){
        ModelAndView mav = new ModelAndView();
        passengersService.cancelPassenger(ticketId);
        ticketService.cancelTicket(authentication.getName());
        mav.setViewName("redirect:/flightslist/myflights");
        return mav;
    }
}
