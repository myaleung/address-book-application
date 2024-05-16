package com.digitalfuturescorp.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBookInterface {
    private AddressBook addressBook;
    private Scanner theScanner;

    public AddressBookInterface(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void start(Scanner testScanner) {
        if (testScanner != null) {
            this.theScanner = testScanner;
        } else {
            this.theScanner = new Scanner(System.in);
        }
        mainMenu();
    }

    public void mainMenu() {
        try {
            String message = """
                Please select and enter a number from the following options:
                    1. View all contacts
                    2. Add a contact
                    3. Edit a contact
                    4. Delete a contact
                    5. Search for a contact by name
                     
                     or any other number to exit program
                """;
            System.out.println(message);
            String selection = theScanner.nextLine();
//            selectedOption(selection);
        } catch (InputMismatchException e) {
            System.out.println("That's not a valid option");
        }
    }

    public void selectedOption(String option) {
        try {
            switch(option) {
                case "1":
                    //go to view contacts
                    goToViewContacts();
                    break;
                case "2":
                    //go to add a new contact
                    gotToAddNewContact();
                    break;
                case "3":
                    //go to edit a contact
                    goToEditContacts();
                    break;
                case "4":
                    //go to delete a contact
                    gotToDeleteContact();
                    break;
                case "5":
                    //go to search for a contact
                    gotToSearchContact();
                    break;
                default:
                    //closes program
                    theScanner.close();
                    System.out.println("Thank you for using DF Corp Address Book. Good Bye.");
                    System.exit(0);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void goToViewContacts() {
        try {
            System.out.print("Here are all the contacts in your address book:");
            ArrayList<Contact> myAddressBook = addressBook.viewContacts();
            for(Contact contact : myAddressBook) {
                System.out.println(contact);
            }
            mainMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void gotToAddNewContact() {
        try {
            System.out.println("""
                    Adding a new contact:
                    Please enter their first name:
                    """);
            String fName = theScanner.next();
            System.out.println("Please enter their surname:");
            String lName = theScanner.next();
            System.out.println("Please enter their email address:");
            String email = theScanner.next();
            System.out.println("Please enter their phone number:");
            String phoneNumber = theScanner.next();
            addNewContact(fName, lName, email, phoneNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addNewContact(String fName, String lName, String email, String phoneNumber) {
        Contact newContact = new Contact(fName, lName, email, phoneNumber);
        addressBook.addContact(newContact);
        System.out.println("New contact added");
        mainMenu();
    }

    private void goToEditContacts() {
        try {
            System.out.print("Enter the name of the contact you wish to edit:");
            String editContactSearch = theScanner.next();
            Contact contactResult = addressBook.searchContacts(editContactSearch);
            String contactResultName = contactResult.getName();
            System.out.printf("I've found %s, what field would you like to edit? %n 1. First Name %n 2. Surname %n 3. Email Address %n 4. Phone Number", contactResultName);
            String editOption = theScanner.nextLine();
            editContact(editOption, contactResult);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editContact(String option, Contact result) {
        switch (option) {
            case "1":
                //edit first name
                System.out.println("Enter new first name:");
                result.setFirstName(theScanner.next());
                System.out.println("Contact Updated");
                mainMenu();
                break;
            case "2":
                //edit last name
                System.out.println("Enter new surname:");
                result.setSurname(theScanner.next());
                System.out.println("Contact Updated");
                mainMenu();
                break;
            case "3":
                //edit email address
                System.out.println("Enter new email address:");
                result.setEmail(theScanner.next());
                System.out.println("Contact Updated");
                mainMenu();
                break;
            case "4":
                //edit phone number
                System.out.println("Enter new phone number:");
                result.setPhoneNumber(theScanner.next());
                System.out.println("Contact Updated");
                mainMenu();
                break;
        }
    }

    private void gotToDeleteContact() {
        try {
            System.out.print("Enter the name of the contact you wish to delete:");
            String contactToDelete = theScanner.next();
            Contact contactToDeleteSearch = addressBook.searchContacts(contactToDelete);
            String contactToDeleteResultName = contactToDeleteSearch.getName();
            addressBook.deleteContact(contactToDeleteSearch);
            System.out.printf("Contact %s deleted", contactToDeleteResultName);
            mainMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void gotToSearchContact() {
        try {
            System.out.print("Enter the name of the contact:");
            String searchName = theScanner.next();
            Contact searchResults = addressBook.searchContacts(searchName);
            System.out.println(searchResults);
            mainMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
