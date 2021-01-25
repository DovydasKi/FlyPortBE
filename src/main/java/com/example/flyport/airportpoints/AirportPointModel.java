package com.example.flyport.airportpoints;

public class AirportPointModel {
    private int airportPointId;
    private String type;
    private String pointNumber;

    AirportPointModel(int airportPointId, String type, String pointNumber) {
        this.airportPointId = airportPointId;
        this.type = type;
        this.pointNumber = pointNumber;
    }

    public int getAirportPointId() {
        return airportPointId;
    }

    public String getType() {
        return type;
    }

    public String getPointNumber() {
        return pointNumber;
    }
}
