package com.digitalfuturescorp.app;

import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public AddressBook() {
    }

    public void addContact(String fName, String lName, String email, String phoneNumber) {
        Contact entry = new Contact(fName, lName, email, phoneNumber);
        contacts.add(entry);
    }

    public ArrayList<Contact> viewContacts() {
        return contacts;
    }

    public void
}
