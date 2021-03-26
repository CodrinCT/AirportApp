package com.example.AirportApplication.Dao;
import com.example.AirportApplication.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUserName(@Param("username") String username);

}
