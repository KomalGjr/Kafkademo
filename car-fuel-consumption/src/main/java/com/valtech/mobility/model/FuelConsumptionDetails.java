package com.valtech.mobility.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class FuelConsumptionDetails {

    private String carId;

    private Double fuelUsed;

    @Override
    public String toString() {
        return "FuelConsumptionDetails{" +
                "carId='" + carId + '\'' +
                ", fuelUsed=" + fuelUsed +
                '}';
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }
}
