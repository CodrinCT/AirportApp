package com.example.AirportApplication.Dao;


import com.example.AirportApplication.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class RegistrationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void enable(String verificationCode) {
        jdbcTemplate.update("UPDATE users SET enabled = 1 WHERE verification_code = ?", verificationCode);
    }

    public User getUserByRegistrationCode(String verificationCode){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE verification_code = ?",this::getUser, verificationCode);
    }

//    public String isEnabled(String verificationCode){
//        return jdbcTemplate.queryForObject("SELECT enabled FROM users WHERE verification_code = ?", this::getEnabled, verificationCode);
//    }

    public Boolean checkIsEnabledByName(String name){
        return jdbcTemplate.queryForObject("SELECT enabled FROM users WHERE username = ?", this::getEnabled, name);
    }
    public void registerUser(User user) {
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String verificationCode = user.getVerificationCode();
        String role = user.getRole();
        jdbcTemplate.update("INSERT INTO users(username, email, password, verification_code, role) VALUES (?,?,?,?,?)", name, email, password, verificationCode,role);
    }
    public boolean getEnabled(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setEnabled(resultSet.getBoolean("enabled"));
        boolean enabled = user.isEnabled();
        return enabled;
    }
    public User getUser(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setVerificationCode(resultSet.getString("verification_code"));
        user.setVerificationCode(resultSet.getString("enabled"));
        return user;

    }
    public String getEmail(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        String email;
        user.setEmail(resultSet.getString("email"));
        email = user.getEmail();
        return email;
    }
    public String getName( ResultSet resultSet, int rowNum)throws SQLException {
            User userModel = new User();
            String name;
            userModel.setUsername(resultSet.getString("username"));
            name = userModel.getUsername();
            return name;
    }
    public List<String> getUserEmail() {
            return jdbcTemplate.query("select email from users", this::getEmail);
    }
    public List<String> getUserName(){
        return jdbcTemplate.query("SELECT username from users", this::getName);
    }
}
