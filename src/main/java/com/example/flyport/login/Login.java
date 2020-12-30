package com.example.flyport.login;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    private @Id String login;
    private String password;

    Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Login() {}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
