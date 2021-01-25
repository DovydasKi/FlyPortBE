package com.example.flyport.flightSearch;

import java.sql.Date;

public class FlightSearchInputModel {
    private String flightNumber;
    private Date flightDate;

    FlightSearchInputModel(String flightNumber, Date flightDate) {
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }
}
