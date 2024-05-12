package com.digitalfuturescorp.app;

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
        return email;
    }

    public String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) throw new IllegalArgumentException("Phone number cannot be null or empty");
        return phoneNumber;
    }
}
