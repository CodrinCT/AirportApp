package com.example.AirportApplication.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "")
@NoArgsConstructor
@AllArgsConstructor
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;
    @Column(name = "name")
    private String className;
    @Column(name = "price")
    private String classTicketPrice;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTicketPrice() {
        return classTicketPrice;
    }

    public void setClassTicketPrice(String classTicketPrice) {
        this.classTicketPrice = classTicketPrice;
    }
}
