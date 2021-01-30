package com.example.flyport.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCLogin {
    //private String login = System.getenv("login");
    //private String password = System.getenv("password");

    public Connection connection;

    public void beginDBSession() throws ClassNotFoundException, SQLException {
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);

        String serverName = "localhost";
        String mydatabase = "flyport";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

        this.connection = DriverManager.getConnection("jdbc:mysql://flyportdatabase.mysql.database.azure.com:3306/flyport?serverTimezone=UTC", "superuser@flyportdatabase", "Flyport001");
    }
}
