package com.example.AirportApplication.Services;


import com.example.AirportApplication.Dao.FlightInfoDao;
import com.example.AirportApplication.Dao.FlightsDao;
import com.example.AirportApplication.Dao.PassengerDao;
import com.example.AirportApplication.Models.FlightInfoModel;
import com.example.AirportApplication.Models.FlightsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FlightsService {

    @Autowired
    FlightsDao flightsDao;

    @Autowired
    FlightInfoDao flightInfoDao;

    @Autowired
    PassengerDao passengerDao;

//    int secondsPassed = 0;
//    Timer myTimer = new Timer();
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            secondsPassed++;
//        }
//    }
    public List<FlightsModel> getAllFlights(){

        return flightsDao.showAllFlights();
    }

    public FlightInfoModel getById(int id){
        return flightInfoDao.getFlightInfoById(id);
    }

    public FlightsModel getFlightById(int id){
        return flightsDao.getFlightById(id);
    }
    public List<FlightsModel> showFlightsByOriginAndDestination(String origin, String destination){
        return flightsDao.showFlightsByOriginAndDestination(origin,destination);
    }

    public List<FlightsModel> showFlightsByOrigin(String origin){
        return flightsDao.showFlightsByOrigin(origin);
    }

    public List<FlightsModel> showFlightsByDestination(String destination){
        return flightsDao.showFlightsByDestination(destination);
    }

    public Integer getAvailableNumberOfSits(int flightId){
        return flightsDao.getAvailableSits(flightId);
    }

    public void makeFlight(String origin, String destination, String timeOfDeparture,
                           String timeOfArrival, String timeUntilArrival, int totalNrOfSits, int availableNrOfSits){
        flightsDao.makeFlight(origin,destination,timeOfDeparture,timeOfArrival,timeUntilArrival,totalNrOfSits, availableNrOfSits);
    }

    public void deleteFlight(int flightId){
        flightsDao.deleteFlight(flightId);
    }

    public Integer doesFlightExists(int flightId){
        int itIs = flightsDao.isFlightThere(flightId);
        return itIs;
    }

    public List<FlightsModel> getMyFlights(String passengerName){
        List<FlightsModel> flightsModel = new ArrayList<>();
        List<Integer> flightsIds=passengerDao.getFlightIdByPassengerName(passengerName);
        for (int id: flightsIds) {
            flightsModel.add(flightsDao.getMyFlights(id));
        }
        return flightsModel;
    }
}
