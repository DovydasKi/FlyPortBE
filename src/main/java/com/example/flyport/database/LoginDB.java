package com.example.flyport.database;

import com.example.flyport.login.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    private JDBCLogin jdbc = new JDBCLogin();

    public ResultSet login(Login login) throws Exception {
        jdbc.beginDBSession();
        String query = "SELECT USER_ID FROM USER_LOGIN WHERE EMAIL = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setString(1, login.getLogin());
        preparedStatement.setString(2, login.getPassword());
        return preparedStatement.executeQuery();
    }
}
