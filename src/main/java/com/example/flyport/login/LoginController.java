package com.example.flyport.login;

import com.example.flyport.database.JDBCLogin;
import com.example.flyport.database.LoginDB;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;

@RestController
public class LoginController {
    private LoginDB loginDb = new LoginDB();
    private Gson parser = new Gson();

    @PostMapping("/login")
    public String login(@RequestBody Login login) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = loginDb.login(login);

        if(resultSet.next()) {
            LoginResponse loginResponse = new LoginResponse(resultSet.getInt(1));
            String json = parser.toJson(loginResponse);
            return json;
        } else if (resultSet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbbiden");
        }
    }
}
