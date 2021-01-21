package com.example.flyport.flightAirportPoints;

public class FlightAirportPointsModel {
    FlightAirportPointModel registrationPoint;
    FlightAirportPointModel securityPoint;
    FlightAirportPointModel passportPoint;
    FlightAirportPointModel boardingGates;

    FlightAirportPointsModel(FlightAirportPointModel registrationPoint, FlightAirportPointModel securityPoint, FlightAirportPointModel passportPoint, FlightAirportPointModel boardingGates) {
        this.registrationPoint = registrationPoint;
        this.securityPoint = securityPoint;
        this.passportPoint = passportPoint;
        this.boardingGates = boardingGates;
    }

    public FlightAirportPointModel getRegistrationPoint() {
        return registrationPoint;
    }

    public FlightAirportPointModel getSecurityPoint() {
        return securityPoint;
    }

    public FlightAirportPointModel getPassportPoint() {
        return passportPoint;
    }

    public FlightAirportPointModel getBoardingGates() {
        return boardingGates;
    }
}
