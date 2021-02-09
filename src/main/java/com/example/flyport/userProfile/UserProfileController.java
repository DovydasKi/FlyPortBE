package com.example.flyport.userProfile;

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
public class UserProfileController {
    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();

    @GetMapping("profile/{id}")
    private String getUserProfile(@PathVariable int id) throws SQLException, ClassNotFoundException{
        jdbc.beginDBSession();

        String query = "SELECT * FROM USER A, USER_LOGIN B WHERE A.USER_ID=? AND A.USER_ID=B.USER_ID";
        PreparedStatement preparedStatement = jdbc.connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String personalCode = resultSet.getString(4);
            String documentNumber = resultSet.getString(5);
            String email = resultSet.getString(7);
            String password = resultSet.getString(8);

            UserLoginInfoModel userLoginInfoModel = new UserLoginInfoModel(email, password);
            UserPersonalInfoModel userPersonalInfoModel = new UserPersonalInfoModel(name, surname, personalCode, documentNumber);
            UserProfileModel userProfileModel = new UserProfileModel(userLoginInfoModel, userPersonalInfoModel);

            String json = parser.toJson(userProfileModel);
            return json;
        } else if (resultSet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}
