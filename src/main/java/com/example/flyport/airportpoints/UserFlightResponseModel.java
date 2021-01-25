package com.example.flyport.airportpoints;

public class UserFlightResponseModel {
    private int userFlightId;

    UserFlightResponseModel(int userFlightId) {
        this.userFlightId = userFlightId;
    }

    public int getUserFlightId() {
        return userFlightId;
    }
}
