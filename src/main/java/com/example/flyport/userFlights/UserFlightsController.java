package com.example.flyport.userFlights;

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
import java.util.ArrayList;
import java.util.Date;

@RestController
public class UserFlightsController {
    private final JDBCLogin jdbc = new JDBCLogin();
    private final Gson parser = new Gson();

    @GetMapping("flights/{userId}")
    private String getUserFlights(@PathVariable int userId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = "SELECT A.USER_FLIGHT_ID, B.FLIGHT_ID, B.FLIGHT_NUMBER, B.FLIGHT_DATE, B.AIRLINES, B.FROM, B.TO, A.COMPLETED FROM USER_FLIGHT A, FLIGHT B WHERE A.USER_ID = ? AND A.FLIGHT_ID = B.FLIGHT_ID";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        UserFlightsModel userFlightsModel = this.convertToFlightsModel(resultSet);

        return parser.toJson(userFlightsModel);
    }

    private UserFlightsModel convertToFlightsModel(ResultSet resultSet) throws SQLException {
        ArrayList<UserFlightModel> userFlightModels = new ArrayList<>();

        while(resultSet.next()) {
            int userFlightId = resultSet.getInt(1);
            int flightId = resultSet.getInt(2);
            String flightNumber = resultSet.getString(3);
            Date flightDate = resultSet.getDate(4);
            String airlines = resultSet.getString(5);
            String fromCity = resultSet.getString(6);
            String toCity = resultSet.getString(7);
            boolean completed = resultSet.getBoolean(8);

            UserFlightModel userFlightModel = new UserFlightModel(userFlightId, completed, flightId, flightNumber, flightDate, airlines, fromCity, toCity);
            userFlightModels.add(userFlightModel);
        }

        if(userFlightModels.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        } else {
            return new UserFlightsModel(userFlightModels);
        }
    }
}
