package com.example.flyport.flightSearch;

import com.example.flyport.database.JDBCLogin;
import com.example.flyport.login.Login;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class FlightInfoController {

    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();

    @PostMapping("flights/search")
    public String searchFlight(@RequestBody FlightSearchInputModel flightSearchInputModel) throws ClassNotFoundException, SQLException {
        jdbc.beginDBSession();

        String query = "SELECT * FROM FLIGHT WHERE FLIGHT_NUMBER = ? AND FLIGHT_DATE = ?";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setString(1, flightSearchInputModel.getFlightNumber());
        preparedStatement.setDate(2, flightSearchInputModel.getFlightDate());

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int flightId = resultSet.getInt(1);
            String flightNumber = resultSet.getString(2);
            Date flightDate = resultSet.getDate(3);
            String airlines = resultSet.getString(4);
            String fromCity = resultSet.getString(5);
            String toCity = resultSet.getString(6);
            boolean passportControl = resultSet.getBoolean(7);
            FlightInfoModel flightInfoModel = new FlightInfoModel(flightId, flightNumber, flightDate, airlines, fromCity, toCity, passportControl);
            return parser.toJson(flightInfoModel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }
}
