package com.digitalfuturescorp.app;

import com.digitalfuturescorp.app.utils.Validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public AddressBook() {
    }

    public void addContact(Contact contactEntry) {
        if (Validation.isNull(contactEntry)) throw new IllegalArgumentException("Contact cannot be null");
        for(Contact contact : contacts) {
            if (contact.getEmail().contains(contactEntry.getEmail())) throw new IllegalArgumentException("Email already exists in contacts");
            if (contact.getPhoneNumber().contains(contactEntry.getPhoneNumber())) throw new IllegalArgumentException("Phone number already exists in contacts");
        }
        contacts.add(contactEntry);
    }

    public ArrayList<Contact> viewContacts() {
        if (contacts.isEmpty()) throw new IllegalStateException("Address book is empty");
        Collections.sort(contacts);
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

    public void deleteContact(Contact contactToDelete) {
        contacts.remove(contactToDelete);
    }
}
