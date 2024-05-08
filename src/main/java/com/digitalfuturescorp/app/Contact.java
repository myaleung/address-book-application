package com.digitalfuturescorp.app;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Contact(String fName, String lName, String email, String phoneNumber) {
        if (fName == null || fName.trim().isEmpty()) throw new IllegalArgumentException("First Name cannot be null or empty");
        if (lName == null || lName.trim().isEmpty()) throw new IllegalArgumentException("First Name cannot be null or empty");
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");

        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
}
