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

    public String getSurname() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String validateName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        return name;
    }

    public String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if(!Validation.matchesEmailRegEx(email)) throw new IllegalArgumentException("Email does not match required pattern");
        return email;
    }

    public String validatePhoneNumber(String phoneNumber) {
        final String phoneNumRegEx = "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$";
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (!phoneNumber.matches(phoneNumRegEx)) throw new IllegalArgumentException("Phone number does not have 11 digits");
        return phoneNumber;
    }
}
