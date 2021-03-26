package com.example.AirportApplication.Services;


import com.example.AirportApplication.Dao.FlightInfoDao;
import com.example.AirportApplication.Dao.FlightsDao;
import com.example.AirportApplication.Models.FlightInfoModel;
import com.example.AirportApplication.Models.FlightsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FlightsService {

    @Autowired
    FlightsDao flightsDao;

    @Autowired
    FlightInfoDao flightInfoDao;

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
}
