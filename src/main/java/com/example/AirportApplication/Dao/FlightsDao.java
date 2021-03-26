package com.example.AirportApplication.Dao;

import com.example.AirportApplication.Models.FlightsModel;
import org.hibernate.engine.query.ParameterRecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FlightsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<FlightsModel> showAllFlights(){
        return jdbcTemplate.query("SELECT * FROM flights",this::getFlight);

    }
    private FlightsModel getFlight (ResultSet resultSet, int rowNum) throws SQLException {
        FlightsModel flightsModel = new FlightsModel();
        flightsModel.setFlightId(resultSet.getInt("id"));
        flightsModel.setOrigin(resultSet.getString("Origin"));
        flightsModel.setDestination(resultSet.getString("Destination"));
        flightsModel.setTimeUntilArrival(resultSet.getString("time_until_arrival"));
        flightsModel.setTimeOfArrival(resultSet.getString("time_of_arrival"));
        flightsModel.setTimeOfDeparture(resultSet.getString("time_of_departure"));
        return flightsModel;
    }

    private Integer getSitsAvailable(ResultSet resultSet, int rowNum) throws SQLException {
        FlightsModel flightsModel = new FlightsModel();
        flightsModel.setNrAvailableSits(resultSet.getInt("available_nr_sits"));
        return flightsModel.getNrAvailableSits();
    }

    public List<FlightsModel> showFlightsByOriginAndDestination(String origin, String destination){
        return jdbcTemplate.query("SELECT * FROM flights WHERE Origin = ? AND Destination = ?", this::getFlight, origin,destination);
    }

    public List<FlightsModel> showFlightsByOrigin(String origin){
        return jdbcTemplate.query("SELECT * FROM flights WHERE Origin =?", this::getFlight, origin);
    }

    public List<FlightsModel> showFlightsByDestination(String destination){
        return jdbcTemplate.query("SELECT * FROM flights WHERE Destination =?", this::getFlight,destination);
    }

    public FlightsModel getFlightById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM flights WHERE id=?", this::getFlight,id);
   }

   public Integer getAvailableSits(int flightId){
        return jdbcTemplate.queryForObject("SELECT available_nr_sits FROM flights WHERE id = ?",this::getSitsAvailable ,flightId);
   }

   public void makeFlight( String origin, String destination, String timeOfDeparture, String timeOfArrival, String timeUntilArrival, int totalNrOfSits, int availableNrOfSits){
        jdbcTemplate.update("insert into flights(Origin,Destination,time_until_arrival,time_of_arrival," +
                "time_of_departure,total_nr_of_sits,available_nr_sits)values(?,?,?,?,?,?,?)",origin,destination,timeOfDeparture,timeOfArrival,timeUntilArrival,totalNrOfSits, availableNrOfSits);
   }

   public void deleteFlight(int flightId){
        jdbcTemplate.update("delete from flights where id=?", flightId);
   }

   public Integer isFlightThere(int flightId){
        try {
            jdbcTemplate.queryForObject("SELECT * FROM flights WHERE id=?",this::getFlight ,flightId);
        }catch (EmptyResultDataAccessException exception){
            return 0;
        }
        return 1;
   }
}
