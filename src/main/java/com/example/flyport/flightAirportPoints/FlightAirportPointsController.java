package com.example.flyport.flightAirportPoints;

import com.example.flyport.database.JDBCLogin;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class FlightAirportPointsController {

    private final JDBCLogin jdbc = new JDBCLogin();
    private final Gson parser = new Gson();

    @GetMapping("flights/airportPoints/{flightId}")
    private String getUserFlights(@PathVariable int flightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String queryForRegistrationTable = "SELECT * FROM AIRPORT_POINT A, USER_FLIGHT E WHERE E.USER_FLIGHT_ID = ? AND E.REGISTRATION_POINT=A.AIRPORT_POINT_ID";
        String queryForAviationSecurityTable = "SELECT * FROM AIRPORT_POINT A, USER_FLIGHT E WHERE E.USER_FLIGHT_ID = ? AND E.SECURITY_POINT=A.AIRPORT_POINT_ID";
        String queryForPassportTable = "SELECT * FROM AIRPORT_POINT A, USER_FLIGHT E WHERE E.USER_FLIGHT_ID = ? AND E.PASSPORT_POINT=A.AIRPORT_POINT_ID";
        String queryForBoardingGates = "SELECT * FROM AIRPORT_POINT A, USER_FLIGHT E WHERE E.USER_FLIGHT_ID = ? AND E.BOARDING_GATES=A.AIRPORT_POINT_ID";

        PreparedStatement registrationPreparedStatement = jdbc.connection.prepareStatement(queryForRegistrationTable);
        PreparedStatement securityPreparedStatement = jdbc.connection.prepareStatement(queryForAviationSecurityTable);
        PreparedStatement passportPreparedStatement = jdbc.connection.prepareStatement(queryForPassportTable);
        PreparedStatement boardingPreparedStatement = jdbc.connection.prepareStatement(queryForBoardingGates);

        registrationPreparedStatement.setInt(1, flightId);
        securityPreparedStatement.setInt(1, flightId);
        passportPreparedStatement.setInt(1, flightId);
        boardingPreparedStatement.setInt(1, flightId);

        ResultSet registrationResultSet = registrationPreparedStatement.executeQuery();
        ResultSet securityResultSet = securityPreparedStatement.executeQuery();
        ResultSet passportResultSet = passportPreparedStatement.executeQuery();
        ResultSet boardingResultSet = boardingPreparedStatement.executeQuery();

        FlightAirportPointsModel flightAirportPointsModel = this.convertToAirportPoints(registrationResultSet, securityResultSet, passportResultSet, boardingResultSet);
        return parser.toJson(flightAirportPointsModel);
    }

    private FlightAirportPointsModel convertToAirportPoints(ResultSet regResultSet, ResultSet secResultSet, ResultSet passResultSet, ResultSet boardResultSet) throws SQLException {
        FlightAirportPointModel registrationPoint = this.getModel(regResultSet);
        FlightAirportPointModel securityPoint = this.getModel(secResultSet);
        FlightAirportPointModel boardingGates = this.getModel(boardResultSet);
        FlightAirportPointModel passportPoint = this.getModel(passResultSet);

        return new FlightAirportPointsModel(registrationPoint, securityPoint, passportPoint, boardingGates);
    }

    private FlightAirportPointModel getModel(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
             return new FlightAirportPointModel(resultSet.getString(3), resultSet.getString(2));
        } else {
            return null;
        }
    }
}
