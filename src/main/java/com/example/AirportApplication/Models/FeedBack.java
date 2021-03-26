package com.example.AirportApplication.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;



public class FeedBack {
   @NotNull
    private String name;
    @NonNull
    @Email
    private String email;
    @NotNull
    @Min(10)
    private String feedback;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @NonNull
    public String getEmail() {
        return email;
    }
    public void setEmail(@NonNull String email) {
        this.email = email;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
