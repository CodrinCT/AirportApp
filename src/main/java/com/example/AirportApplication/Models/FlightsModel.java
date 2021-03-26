package com.example.AirportApplication.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "flights")
public class FlightsModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int flightId;
    @Column(name = "Origin")
    private String origin;
    @Column(name = "Destination")
    private String destination;
    @Column(name = "time_until_arrival")
    private String timeUntilArrival;
    @Column(name = "time_of_arrival")
    private String timeOfArrival;
    @Column(name = "time_of_departure")
    private String timeOfDeparture;
    @Column(name = "total_nr_of_sits")
    private int nrOfSits;
    @Column(name = "available_nr_sits")
    private int nrAvailableSits;

    public FlightsModel(){

    }

    public FlightsModel(int flightId, String origin, String destination, String timeUntilArrival,
                        String timeOfArrival, String timeOfDeparture, int nrOfSits, int nrAvailableSits) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.timeUntilArrival = timeUntilArrival;
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
        this.nrOfSits = nrOfSits;
        this.nrAvailableSits = nrAvailableSits;
    }

    public String getTimeUntilArrival() {
        return timeUntilArrival;
    }

    public void setTimeUntilArrival(String timeUntilArrival) {
        this.timeUntilArrival = timeUntilArrival;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public int getNrOfSits() {
        return nrOfSits;
    }

    public void setNrOfSits(int nrOfSits) {
        this.nrOfSits = nrOfSits;
    }

    public int getNrAvailableSits() {
        return nrAvailableSits;
    }

    public void setNrAvailableSits(int nrAvailableSits) {
        this.nrAvailableSits = nrAvailableSits;
    }
}
