package com.digitalfuturescorp.app;

import com.digitalfuturescorp.app.utils.Validation;

import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public AddressBook() {
    }

    public void addContact(Contact contactEntry) {
        if (Validation.isNull(contactEntry)) throw new IllegalArgumentException("Contact cannot be null");
        contacts.add(contactEntry);
    }

    public ArrayList<Contact> viewContacts() {
        return contacts;
    }

    public Contact searchContacts(String searchTerm) {
        if (Validation.isNull(searchTerm) || Validation.isEmpty(searchTerm)) throw new IllegalArgumentException("Search term cannot be null or empty");
        Contact result = null;
        for(Contact contact : contacts) {
            if (contact.getName().contains(searchTerm)) {
                result = contact;
            }
        }
        return result;
    }
}
