package com.example.flyport.userProfile;

public class UserLoginInfoModel {
    private String email;
    private String password;

    UserLoginInfoModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
