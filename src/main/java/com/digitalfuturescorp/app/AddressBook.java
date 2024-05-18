package com.digitalfuturescorp.app;

import com.digitalfuturescorp.app.utils.Validation;

import java.util.ArrayList;
import java.util.Collections;

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

    public ArrayList<Contact> searchContacts(String searchTerm) {
        if (Validation.isNull(searchTerm) || Validation.isEmpty(searchTerm)) throw new IllegalArgumentException("Search term cannot be null or empty");
        ArrayList<Contact> results = new ArrayList<>();
        for(Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                contact.getEmail().toLowerCase().contains(searchTerm.toLowerCase()) ||
                contact.getPhoneNumber().contains(searchTerm)) {
                results.add(contact);
            }
        }
        return results;
    }

    public void deleteContact(Contact contactToDelete) {
        if (Validation.isNull(contactToDelete)) throw new IllegalArgumentException("Contact to delete cannot be null");
        if (!contacts.contains(contactToDelete)) throw new IllegalArgumentException("Contact to delete does not exist");
        contacts.remove(contactToDelete);
    }

    public boolean deleteAllContacts() {
        if (contacts.isEmpty()) throw new IllegalStateException("Address book is empty");
        if (!contacts.isEmpty()) contacts.clear();
        return contacts.isEmpty();
    }
}
