package com.example.AirportApplication.Services;

import com.example.AirportApplication.Dao.FlightInfoDao;
import com.example.AirportApplication.Dao.PassengerDao;
import com.example.AirportApplication.Models.PassengerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengersService {

    @Autowired
    PassengerDao passengerDao;

    @Autowired
    FlightInfoDao flightInfoDao;

    public void registerPassengerToFlight(String passengerName, int ticketId, int passengerFlightId){
        passengerDao.registerPassengerToFlight(passengerName, ticketId, passengerFlightId);
    }

    public List<PassengerModel> getPassengersByFlightId(int passengerFlightId){
        return passengerDao.getPassengersByFlightId(passengerFlightId);
    }

    public void cancelPassenger(int ticketId){
        passengerDao.cancelPassenger(ticketId);
    }

    public void changeNumberOfAvailableSeats(int flightId){
        int numberOfSeats = flightInfoDao.getNumberOfSeatsById(flightId);
//        int nrOfAvailableSeats = flightInfoDao.getNumberOfAvailableSeatsById(flightId);
        int nrOfPassengers = passengerDao.getNumberOfPassengers();
        flightInfoDao.setNumberOfAvailableSeats(nrOfPassengers,numberOfSeats);

    }
    public Integer getNumberOfAvailableSeatsByFlightId(int flightId){
        return flightInfoDao.getNumberOfAvailableSeatsById(flightId);
    }
}
