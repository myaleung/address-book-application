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

    public void editContact(Contact contactToEdit, String fieldToEdit, String newValue) {
        if (Validation.isNull(contactToEdit)) throw new IllegalArgumentException("Contact to edit cannot be null");
        if (Validation.isNull(fieldToEdit)) throw new IllegalArgumentException("Field to edit cannot be null");
        if (Validation.isEmpty(newValue)) throw new IllegalArgumentException("New value cannot be empty");
        if (!contacts.contains(contactToEdit)) throw new IllegalArgumentException("Contact to edit does not exist");
        switch (fieldToEdit.toLowerCase()) {
            case "firstname":
                contactToEdit.setFirstName(newValue);
                break;
            case "lastname":
                contactToEdit.setSurname(newValue);
                break;
            case "email":
                contactToEdit.setEmail(newValue);
                break;
            case "phone":
                contactToEdit.setPhoneNumber(newValue);
                break;
        }
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
            if (contact.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
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
}
