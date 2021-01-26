package com.example.flyport.airportpoints;

import com.example.flyport.database.JDBCLogin;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class AirportInfoController {
    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();
    private AirportPointQueries airportPointQueries = new AirportPointQueries();

    @PostMapping("user/flight")
    private String registerUserFlight(@RequestBody UserFlightCreationModel userFlightCreationModel) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.registrationPoint.registerUserFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, userFlightCreationModel.getUserId());
        preparedStatement.setInt(2, userFlightCreationModel.getFlightId());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int lastIndex = resultSet.getInt(1);
            preparedStatement.close();
            UserFlightResponseModel userFlightResponseModel = new UserFlightResponseModel(lastIndex);
            return parser.toJson(userFlightResponseModel);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    @GetMapping("user/flight/{userFlightId}/point/registration")
    private String findRegistrationAirportPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.registrationPoint.addRegistrationPointToUserFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, userFlightId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        String queryForUpdatingQueue = airportPointQueries.registrationPoint.addQueueToRegistrationPoint;
        PreparedStatement queueUpdatingStatement = jdbc.connection.prepareStatement(queryForUpdatingQueue);
        queueUpdatingStatement.setInt(1, userFlightId);
        queueUpdatingStatement.executeUpdate();
        queueUpdatingStatement.close();

        String queryForRegistrationPoint = airportPointQueries.registrationPoint.getRegistrationPoint;
        PreparedStatement pointStatement = jdbc.connection.prepareStatement(queryForRegistrationPoint);
        pointStatement.setInt(1, userFlightId);
        ResultSet resultSet = pointStatement.executeQuery();
        if (resultSet.next()) {
            int pointId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String number = resultSet.getString(3);
            AirportPointModel airportPointModel = new AirportPointModel(pointId, type, number);
            return parser.toJson(airportPointModel);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    @PutMapping("user/flight/{userFlightId}/point/registration")
    private void leaveRegistrationPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.registrationPoint.removeQueueFromRegistrationPoint;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        int success = preparedStatement.executeUpdate();
        if (success <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "OK");
        }

    }

    @GetMapping("user/flight/{userFlightId}/point/security")
    private String findSecurityAirportPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.securityPoint.addSecurityPointToUserFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        String queryForUpdatingQueue = airportPointQueries.securityPoint.addQueueToSecurityPoint;
        PreparedStatement queueUpdatingStatement = jdbc.connection.prepareStatement(queryForUpdatingQueue);
        queueUpdatingStatement.setInt(1, userFlightId);
        queueUpdatingStatement.executeUpdate();
        queueUpdatingStatement.close();

        String queryForSecurityPoint = airportPointQueries.securityPoint.getSecurityPoint;
        PreparedStatement pointStatement = jdbc.connection.prepareStatement(queryForSecurityPoint);
        pointStatement.setInt(1, userFlightId);
        ResultSet resultSet = pointStatement.executeQuery();
        if (resultSet.next()) {
            int pointId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String number = resultSet.getString(3);
            AirportPointModel airportPointModel = new AirportPointModel(pointId, type, number);
            return parser.toJson(airportPointModel);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    @PutMapping("user/flight/{userFlightId}/point/security")
    private void leaveSecurityPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.securityPoint.removeQueueFromSecurityPoint;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        int success = preparedStatement.executeUpdate();
        if (success <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "OK");
        }

    }

    @GetMapping("user/flight/{userFlightId}/point/passport")
    private String findPassportAirportPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.passportPoint.addPassportPointToUserFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        String queryForUpdatingQueue = airportPointQueries.passportPoint.addQueueToPassportPoint;
        PreparedStatement queueUpdatingStatement = jdbc.connection.prepareStatement(queryForUpdatingQueue);
        queueUpdatingStatement.setInt(1, userFlightId);
        queueUpdatingStatement.executeUpdate();
        queueUpdatingStatement.close();

        String queryForSecurityPoint = airportPointQueries.passportPoint.getPassportPoint;
        PreparedStatement pointStatement = jdbc.connection.prepareStatement(queryForSecurityPoint);
        pointStatement.setInt(1, userFlightId);
        ResultSet resultSet = pointStatement.executeQuery();
        if (resultSet.next()) {
            int pointId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String number = resultSet.getString(3);
            AirportPointModel airportPointModel = new AirportPointModel(pointId, type, number);
            return parser.toJson(airportPointModel);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    @PutMapping("user/flight/{userFlightId}/point/passport")
    private void leavePassportPoint(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.passportPoint.removeQueueFromPassportPoint;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        int success = preparedStatement.executeUpdate();
        if (success <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "OK");
        }

    }

    @GetMapping("user/flight/{userFlightId}/point/boarding")
    private String findBoardingAirportGates(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.boardingGates.addBoardingGatesToUserFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        String queryForSecurityPoint = airportPointQueries.boardingGates.getBoardingGates;
        PreparedStatement pointStatement = jdbc.connection.prepareStatement(queryForSecurityPoint);
        pointStatement.setInt(1, userFlightId);
        ResultSet resultSet = pointStatement.executeQuery();
        if (resultSet.next()) {
            int pointId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String number = resultSet.getString(3);
            AirportPointModel airportPointModel = new AirportPointModel(pointId, type, number);
            return parser.toJson(airportPointModel);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    @PutMapping("user/flight/{userFlightId}/complete")
    private void completeFlight(@PathVariable int userFlightId) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String query = airportPointQueries.boardingGates.completeFlight;
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, userFlightId);
        int success = preparedStatement.executeUpdate();
        if (success <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "OK");
        }

    }
}
