package com.digitalfuturescorp.app;

import com.digitalfuturescorp.app.utils.Validation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBookInterface {
    private AddressBook addressBook;
    private Scanner theScanner;

    public AddressBookInterface(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void start(Scanner aScanner) {
        this.theScanner = aScanner;
        mainMenu();
    }

    public void mainMenu() {
            try {
                do {
                    String message = """
                    Please select and enter a number from the following options:\r
                        1. Add a contact\r
                        2. Edit a contact\r
                        3. Delete a contact\r
                        4. View all contacts\r
                        5. Search for a contact by name\n
                         or 0 to exit program
                    """;
                    System.out.println(message);
                    String option = theScanner.nextLine();
                    if (Validation.matchesMainMenuOptionRegEx(option)) {
                        selectedOption(option);
                        break;
                    } else {
                        System.out.println("Invalid option.");
                    }
                } while (true);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void selectedOption(String option) {
        try {
            switch(option) {
                case "1":
                    //go to add a new contact
                    gotToAddNewContact();
                    break;
                case "2":
                    //go to edit a contact
                    goToEditContacts();
                    break;
                case "3":
                    //go to delete a contact
                    gotToDeleteContact();
                    break;
                case "4":
                    //go to view contacts
                    goToViewContacts();
                    break;
                case "5":
                    //go to search for a contact
                    gotToSearchContact();
                    break;
                case "0":
                    //closes program
                    exitProgram();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void goToViewContacts() {
        try {
            System.out.println("Here are all the contacts in your address book:");
            ArrayList<Contact> myAddressBook = addressBook.viewContacts();
            for(Contact contact : myAddressBook) {
                System.out.println(contact);
            }
            routeTheUser();
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
            String fName = theScanner.nextLine();
            System.out.println("Please enter their surname:");
            String lName = theScanner.nextLine();
            System.out.println("Please enter their email address:");
            String email = theScanner.nextLine();
            System.out.println("Please enter their phone number:");
            String phoneNumber = theScanner.nextLine();
            addNewContact(fName, lName, email, phoneNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addNewContact(String fName, String lName, String email, String phoneNumber) {
        Contact newContact = new Contact(fName, lName, email, phoneNumber);
        addressBook.addContact(newContact);
        System.out.println("New contact added");
        routeTheUser();
    }

    private void goToEditContacts() {
        try {
            System.out.print("Enter the name of the contact you wish to edit:");
            String editContactSearch = theScanner.nextLine();
            ArrayList<Contact> contactResults = addressBook.searchContacts(editContactSearch);
            String contactResultName = contactResults.get(0).getName();
            System.out.printf("I've found %s, what field would you like to edit? %n 1. First Name %n 2. Surname %n 3. Email Address %n 4. Phone Number %n", contactResultName);
            String editOption = theScanner.nextLine();
            editContact(editOption, contactResults.get(0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editContact(String option, Contact result) {
        switch (option) {
            case "1":
                //edit first name
                System.out.println("Enter new first name:");
                result.setFirstName(theScanner.nextLine());
                System.out.println("Contact Updated");
                routeTheUser();
                break;
            case "2":
                //edit last name
                System.out.println("Enter new surname:");
                result.setSurname(theScanner.nextLine());
                System.out.println("Contact Updated");
                routeTheUser();
                break;
            case "3":
                //edit email address
                System.out.println("Enter new email address:");
                result.setEmail(theScanner.nextLine());
                System.out.println("Contact Updated");
                routeTheUser();
                break;
            case "4":
                //edit phone number
                System.out.println("Enter new phone number:");
                result.setPhoneNumber(theScanner.nextLine());
                System.out.println("Contact Updated");
                routeTheUser();
                break;
        }
    }

    private void gotToDeleteContact() {
        try {
            System.out.print("Enter the name of the contact you wish to delete:");
            String contactToDelete = theScanner.nextLine();
            Contact contactToDeleteSearch = addressBook.searchContacts(contactToDelete).get(0);
            addressBook.deleteContact(contactToDeleteSearch);
            System.out.printf("Contact %s deleted.%n", contactToDeleteSearch.getName());
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void gotToSearchContact() {
        try {
            System.out.println("Enter the name of the contact:");
            String searchName = theScanner.nextLine();
            ArrayList<Contact> searchResults = addressBook.searchContacts(searchName);
            if (searchResults.isEmpty()) {
                System.out.println("Found no matching contact");
            } else {
                System.out.println("Found matching contact/s:");
                System.out.println(searchResults);
            }
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void routeTheUser() {
        try {
            do {
                String message = """
                What would you like to do next?\r
                   1 . Go back to main menu\r
                   0 . Exit program 
                """;
                System.out.println(message);
                String option = theScanner.nextLine();
                if (Validation.matchesExitMenuOptionRegEx(option)) {
                    switch (option) {
                        case "1":
                            mainMenu();
                            break;
                        case "0":
                            //closes program
                            exitProgram();
                            break;
                    }
                } else {
                    System.out.println("Invalid option.");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void exitProgram() {
        theScanner.close();
        System.out.println("Thank you for using DF Corp Address Book. Good Bye.");
        System.exit(0);
    }
}
