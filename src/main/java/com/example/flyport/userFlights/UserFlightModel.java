package com.example.flyport.userFlights;

import java.util.Date;

public class UserFlightModel {
    private int userFlightId;
    private boolean completed;
    private int flightId;
    private String flightNumber;
    private Date flightDate;
    private String airlines;
    private String fromCity;
    private String toCity;

    UserFlightModel(int userFlightId, boolean completed, int flightId, String flightNumber, Date flightDate, String airlines, String fromCity, String toCity) {
        this.userFlightId = userFlightId;
        this.completed = completed;
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.airlines = airlines;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public String getAirlines() {
        return airlines;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public int getUserFlightId() {
        return userFlightId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
