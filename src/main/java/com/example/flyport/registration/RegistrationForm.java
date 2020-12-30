package com.example.flyport.registration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class RegistrationForm {
    private @Id String email;
    private String password;
    private String name;
    private String surname;
    private String personalCode;
    private String documentNumber;

    RegistrationForm(String email, String password, String name, String surname, String personalCode, String documentNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.documentNumber = documentNumber;
    }

    public RegistrationForm() {}

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
