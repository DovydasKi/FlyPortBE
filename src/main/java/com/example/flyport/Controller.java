package com.example.flyport;

import com.example.flyport.database.JDBCLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class Controller {
    private JDBCLogin jdbc = new JDBCLogin();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Woooorld") String name) throws ClassNotFoundException, SQLException {
        jdbc.beginDBSession();
        Statement st = jdbc.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM USER_LOGIN;");

        if(rs.next()) {
            return new Greeting(rs.getInt(1), rs.getString(2));
        }
        return new Greeting(0, "bad");
    }
}
