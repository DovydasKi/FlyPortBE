package com.example.flyport.registration;

import com.example.flyport.database.JDBCLogin;
import com.example.flyport.login.LoginResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class RegistrationController {
    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();

    @PostMapping("/registration")
    public void register(@RequestBody RegistrationForm registrationForm) throws ClassNotFoundException, SQLException {
        jdbc.beginDBSession();

        if (!checkUserExistance(registrationForm.getEmail())) { throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");}

        String query = "INSERT INTO USER(NAME, SURNAME, PERSONAL_CODE, DOCUMENT_NUMBER) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, registrationForm.getName());
        preparedStatement.setString(2, registrationForm.getSurname());
        preparedStatement.setString(3, registrationForm.getPersonalCode());
        preparedStatement.setString(4, registrationForm.getDocumentNumber());
        int successIdentifier = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int lastIndex = resultSet.getInt(1);
            preparedStatement.close();
            insertToUserLoginTable(lastIndex, registrationForm.getEmail(), registrationForm.getPassword());
        }

        if(successIdentifier > 0) {
            throw new ResponseStatusException(HttpStatus.OK, "Success");
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }
    }

    private void insertToUserLoginTable(int id, String email, String password) throws SQLException {
        String query = "INSERT INTO USER_LOGIN(USER_ID, EMAIL, PASSWORD) VALUES(?,?,?)";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        int successIdentifier = preparedStatement.executeUpdate();
        if(successIdentifier > 0) {
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem with data base");
        }

        preparedStatement.close();
    }

    private Boolean checkUserExistance(String email) throws SQLException {
        String query = "SELECT * FROM USER_LOGIN WHERE EMAIL = ?";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            return false;
        } else {
            resultSet.close();
            preparedStatement.close();
            return true;
        }
    }
}
