package com.example.AirportApplication.Dao;

import com.example.AirportApplication.Models.PassengerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class PassengerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public PassengerModel getPassenger(ResultSet resultSet, int rowNum) throws SQLException {
        PassengerModel passengerModel = new PassengerModel();
        passengerModel.setPassengerId(resultSet.getInt("passengers_id"));
        passengerModel.setPassengerName(resultSet.getString("passenger_name"));
        passengerModel.setTicketId(resultSet.getInt("ticket_id"));
        passengerModel.setPassengerFlightId(resultSet.getInt("passenger_flight_id"));
        return passengerModel;
    }

    public List<PassengerModel> getAllPassengers(){
        return jdbcTemplate.query("SELECT * FROM passengers",this::getPassenger);
    }

    public List<PassengerModel> getPassengersByFlightId(int passengerFlightId){
        return jdbcTemplate.query("SELECT * FROM passengers WHERE passenger_flight_id = ?", this::getPassenger, passengerFlightId);
    }

    public List<PassengerModel> getPassengersByTicketId(int ticketId){
        return jdbcTemplate.query("SELECT * FROM passengers WHERE ticket_id = ? ", this::getPassenger, ticketId);
    }

    public PassengerModel getPassengerByPassengerId(int passengerId){
        return jdbcTemplate.queryForObject("SELECT * FROM passenger WHERE passenger_id = ?", this::getPassenger, passengerId);
    }

    public void registerPassengerToFlight(String passengerName, int ticketId, int passengerFlightId){
        jdbcTemplate.update("INSERT INTO passengers(passenger_name,ticket_id,passenger_flight_id) values(?,?,?)", passengerName, ticketId, passengerFlightId);
    }
    public void cancelPassenger(int ticketId){
        jdbcTemplate.update("DELETE FROM passengers WHERE ticket_id = ?", ticketId);
    }

    public Integer getNumberOfPassengers(){
        List<PassengerModel> passengerModelList;
        passengerModelList = jdbcTemplate.query("SELECT * FROM passengers",this::getPassenger);
        return passengerModelList.size();
    }
}
