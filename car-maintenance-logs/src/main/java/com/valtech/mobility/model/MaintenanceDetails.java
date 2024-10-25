package com.valtech.mobility.model;

import lombok.Data;

import java.util.Date;

@Data
public class MaintenanceDetails {

    private String carId;

    private String maintenance;

    private Date date;

    @Override
    public String toString() {
        return "MaintenanceDetails{" +
                "carId='" + carId + '\'' +
                ", maintenance='" + maintenance + '\'' +
                ", date=" + date +
                '}';
    }
}
