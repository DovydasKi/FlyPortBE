package com.example.flyport.userFlights;

import java.util.ArrayList;

public class UserFlightsModel {
    private ArrayList<UserFlightModel> flights;

    UserFlightsModel(ArrayList<UserFlightModel> flights) {
        this.flights = flights;
    }

    public ArrayList<UserFlightModel> getFlights() {
        return flights;
    }
}
