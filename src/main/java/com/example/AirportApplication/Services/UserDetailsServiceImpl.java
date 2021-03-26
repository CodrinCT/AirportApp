package com.example.AirportApplication.Services;

import com.example.AirportApplication.Dao.MyUserDetails;
import com.example.AirportApplication.Dao.UserRepository;
import com.example.AirportApplication.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String name){
        User user = userRepository.getUserByUserName(name);
        if (user != null && user.isEnabled()){
            return new MyUserDetails(user);
        }else if (user == null || !user.isEnabled()){
            throw new UsernameNotFoundException("Could not find user");
        }
        return null;
    }
}
