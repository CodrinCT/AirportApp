package com.example.AirportApplication.Security;

import com.example.AirportApplication.Services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/flightslist/tickets/{id}").hasAuthority("USER")
                .antMatchers("/flightslist/tickets").hasAuthority("USER")
                .antMatchers(HttpMethod.GET,"/passengers/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/cancel/{ticketId}").hasAuthority("ADMIN")
                .antMatchers("/flightslist/makeORremove").hasAuthority("MANAGER")
                .antMatchers( "flightslist/newFlight").hasAuthority("MANAGER")
                .antMatchers( "flightslist/deleteFlight").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/flightslist/information/{flightId}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/flightslist").hasAnyAuthority("USER","ADMIN","MANAGER")
                .antMatchers(HttpMethod.POST,"/flightslist").hasAnyAuthority("USER","ADMIN","MANAGER")
                .antMatchers("/registration","/images/**","/style/**").permitAll()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .antMatchers(HttpMethod.GET,"/verify").permitAll()
                .antMatchers("/feedback").permitAll()
                .and()
                .formLogin().loginPage("/home")
                .permitAll()
                .defaultSuccessUrl("/flightslist",true)
                .failureForwardUrl("/loginFail")
                .and()
                .rememberMe()
                .and()
                .logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .logoutSuccessUrl("/home");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
