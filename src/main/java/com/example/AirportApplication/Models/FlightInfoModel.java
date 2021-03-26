package com.example.AirportApplication.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "flight_personal_info")
public class FlightInfoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idflight_personal_info")
    private int id;
    @Column(name = "class")
    private String planeClass;
    @Column(name = "number_of_sits")
    private int nrOfSits;
    @Column(name = "available_sits")
    private int availableSits;

    public FlightInfoModel(){

    }

    public FlightInfoModel(int id, String planeClass, int nrOfSits, int availableSits) {
        this.id = id;
        this.planeClass = planeClass;
        this.nrOfSits = nrOfSits;
        this.availableSits = availableSits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaneClass() {
        return planeClass;
    }

    public void setPlaneClass(String planeClass) {
        this.planeClass = planeClass;
    }

    public int getNrOfSits() {
        return nrOfSits;
    }

    public void setNrOfSits(int nrOfSits) {
        this.nrOfSits = nrOfSits;
    }

    public int getAvailableSits() {
        return availableSits;
    }

    public void setAvailableSits(int availableSits) {
        this.availableSits = availableSits;
    }
}
