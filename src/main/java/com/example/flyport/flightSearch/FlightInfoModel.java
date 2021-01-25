package com.example.flyport.flightSearch;

import java.sql.Date;

public class FlightInfoModel {
    private int flightId;
    private String flightNumber;
    private Date flightDate;
    private String airlines;
    private String fromCity;
    private String toCity;
    private boolean passportControl;

    FlightInfoModel(int flightId, String flightNumber, Date flightDate, String airlines, String fromCity, String toCity, boolean passportControl) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.airlines = airlines;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.passportControl = passportControl;
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

    public boolean isPassportControl() {
        return passportControl;
    }
}
