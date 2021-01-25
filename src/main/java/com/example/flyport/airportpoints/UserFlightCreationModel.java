package com.example.flyport.airportpoints;

public class UserFlightCreationModel {
    private int userId;
    private int flightId;

    UserFlightCreationModel(int userId, int flightId) {
        this.userId = userId;
        this.flightId = flightId;
    }

    public int getUserId() {
        return userId;
    }

    public int getFlightId() {
        return flightId;
    }

}
