package com.example.flyport.flightAirportPoints;

public class FlightAirportPointModel {
    private String pointNumber;
    private String type;

    FlightAirportPointModel(String pointNumber, String type) {
        this.pointNumber = pointNumber;
        this.type = type;
    }

    public String getPointNumber() {
        return pointNumber;
    }

    public String getType() {
        return type;
    }
}
