package com.example.AirportApplication.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="passengers")
@NoArgsConstructor
@AllArgsConstructor
public class PassengerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passengers_id")
    private int passengerId;
    @Column(name = "ticket_id")
    private int ticketId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "passenger_flight_id")
    private int passengerFlightId;

    public int getPassengerFlightId() {
        return passengerFlightId;
    }

    public void setPassengerFlightId(int passengerFlightId) {
        this.passengerFlightId = passengerFlightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

}
