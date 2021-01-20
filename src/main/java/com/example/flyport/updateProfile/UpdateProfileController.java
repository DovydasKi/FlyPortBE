package com.example.flyport.updateProfile;

import com.example.flyport.database.JDBCLogin;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class UpdateProfileController {
    private JDBCLogin jdbc = new JDBCLogin();
    private Gson parser = new Gson();

    @PutMapping("/profile/{id}")
    public void updateProfile(@RequestBody UpdateProfileModel updateProfileModel, @PathVariable int id) throws SQLException, ClassNotFoundException {
        jdbc.beginDBSession();

        String queryForUserInfoUpdating = "UPDATE USER SET DOCUMENT_NUMBER=? WHERE USER_ID=?";
        String queryForUpdatingUserLoginInfo = "UPDATE USER_LOGIN SET EMAIL=?, PASSWORD=? WHERE USER_ID=?";

        PreparedStatement userInfoStatement = jdbc.connection.prepareStatement(queryForUserInfoUpdating);
        userInfoStatement.setString(1, updateProfileModel.getDocumentNumber());
        userInfoStatement.setInt(2, id);

        PreparedStatement userLoginInfoStatement = jdbc.connection.prepareStatement(queryForUpdatingUserLoginInfo);
        userLoginInfoStatement.setString(1, updateProfileModel.getEmail());
        userLoginInfoStatement.setString(2, updateProfileModel.getPassword());
        userLoginInfoStatement.setInt(3, id);

        int profileUpdatingSuccess = userInfoStatement.executeUpdate();
        if(profileUpdatingSuccess <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }

        profileUpdatingSuccess = userLoginInfoStatement.executeUpdate();
        if(profileUpdatingSuccess <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "OK");
        }
    }
}
