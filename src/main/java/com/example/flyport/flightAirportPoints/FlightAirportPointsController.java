package com.example.flyport.flightAirportPoints;

import com.example.flyport.database.JDBCLogin;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class FlightAirportPointsController {

    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();

    @GetMapping("flights/airportPoints/{flightId}")
    private String getUserFlights(@PathVariable int flightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = "SELECT * FROM AIRPORT_POINT A, AIRPORT_POINT B, AIRPORT_POINT C, AIRPORT_POINT D, USER_FLIGHT E " +
                "WHERE E.USER_FLIGHT_ID = ? " +
                "AND E.REGISTRATION_POINT=A.AIRPORT_POINT_ID " +
                "AND E.SECURITY_POINT=B.AIRPORT_POINT_ID " +
                "AND E.BOARDING_GATES=C.AIRPORT_POINT_ID " +
                "AND E.PASSPORT_POINT=D.AIRPORT_POINT_ID ";

        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, flightId);
        ResultSet resultSet = preparedStatement.executeQuery();

        FlightAirportPointsModel flightAirportPointsModel = this.convertToAirportPoints(resultSet);
        return parser.toJson(flightAirportPointsModel);
    }

        private FlightAirportPointsModel convertToAirportPoints(ResultSet resultSet) throws SQLException {

            if(resultSet.next()) {
                FlightAirportPointModel registrationPoint = new FlightAirportPointModel(resultSet.getString(3), resultSet.getString(2));
                FlightAirportPointModel securityPoint = new FlightAirportPointModel(resultSet.getString(7), resultSet.getString(6));
                FlightAirportPointModel boardingGates = new FlightAirportPointModel(resultSet.getString(11), resultSet.getString(10));
                FlightAirportPointModel passportPoint = new FlightAirportPointModel(resultSet.getString(15), resultSet.getString(14));

                FlightAirportPointsModel flightAirportPointsModel = new FlightAirportPointsModel(registrationPoint, securityPoint, passportPoint, boardingGates);
                return flightAirportPointsModel;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
    }
