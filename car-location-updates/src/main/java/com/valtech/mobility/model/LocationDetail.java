package com.valtech.mobility.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LocationDetail {

    private String carId;

    private Double latitude;

    private Double longitude;

    @Override
    public String toString() {
        return "LocationDetail{" +
                "carId='" + carId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
