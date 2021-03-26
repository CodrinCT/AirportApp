package com.example.AirportApplication.Services;


import com.example.AirportApplication.Dao.ClassDao;
import com.example.AirportApplication.Dao.FlightsDao;
import com.example.AirportApplication.Dao.TicketDao;
import com.example.AirportApplication.Models.ClassModel;
import com.example.AirportApplication.Models.TicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    FlightsDao flightsDao;
    @Autowired
    TicketDao ticketDao;
    @Autowired
    ClassDao classDao;

    public void makeTicket(int flightId, int price, String passengerName){
        ticketDao.makeTicket(flightId,price,passengerName);
    }

    public void cancelTicket(String user){
        ticketDao.cancelTicket(user);
    }

    public String getPriceByName(String className){
       return classDao.getPriceByName(className);
    }

    public TicketModel getTicketByTicketId(String passengerName){
        return ticketDao.getTicketByTicketId(passengerName);
    }

    public List<ClassModel>  getPlaneClass(){
        return classDao.getPlaneClassAndPrice();
    }
    public ClassModel getPlaneClassById(int id){
        return classDao.getPlaneClassAndPriceById(id);
    }
    public String getPriceById(int id){
        return classDao.getPriceById(id);
    }
}
