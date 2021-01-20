package com.example.flyport.updateProfile;

public class UpdateProfileModel {
    private String documentNumber;
    private String email;
    private String password;

    UpdateProfileModel(String documentNumber, String email, String password) {
        this.documentNumber = documentNumber;
        this.email = email;
        this.password = password;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
