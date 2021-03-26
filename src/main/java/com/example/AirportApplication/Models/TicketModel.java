package com.example.AirportApplication.Models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tickets")
public class TicketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tickets_id")
    private int ticketId;
    @Column(name = "flight_id")
    private int flightId;
    @Column(name = "price")
    private String price;
    @Column(name = "passenger_name")
    private String passengerName;

    public TicketModel(){

    }

    public TicketModel(int ticketId, int flightId, String price, String passengerName) {
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.price = price;
        this.passengerName = passengerName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    //private FlightsModel flight;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public void setFlight(FlightsModel flight){
//      this.flight = flight;
//    }
//
//    public FlightsModel getFlight() {
//        return flight;
//    }

}
