package com.example.flyport.userProfile;

public class UserPersonalInfoModel {
    private String name;
    private String surname;
    private String personalCode;
    private String documentNumber;

    UserPersonalInfoModel(String name, String surname, String personalCode, String documentNumber) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
