package com.digitalfuturescorp.app;

import com.digitalfuturescorp.app.utils.Validation;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Contact(String fName, String lName, String email, String phoneNumber) {
        this.firstName = validateName(fName);
        this.lastName = validateName(lName);
        this.email = validateEmail(email);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public String getName() {
        return this.firstName.concat(lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = validateName(name);
    }

    public String getSurname() {
        return this.lastName;
    }

    public void setSurname(String name) {
        this.lastName = validateName(name);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public String validateName(String name) {
        if (Validation.isNull(name) || Validation.isEmpty(name)) throw new IllegalArgumentException("Name cannot be null or empty");
        return name;
    }

    public String validateEmail(String email) {
        if (Validation.isNull(email) || Validation.isEmpty(email)) throw new IllegalArgumentException("Email cannot be null or empty");
        if(!Validation.matchesEmailRegEx(email)) throw new IllegalArgumentException("Email does not match required pattern");
        return email;
    }

    public String validatePhoneNumber(String phoneNumber) {
        if (Validation.isNull(phoneNumber) || Validation.isEmpty(phoneNumber)) throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (!Validation.matchesPhoneNumberRegEx(phoneNumber)) throw new IllegalArgumentException("Phone number does not have 11 digits");
        return phoneNumber;
    }
}
