package com.digitalfuturescorp.app;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookInterface {
    private final AddressBook addressBook;
    private Scanner theScanner;

    public AddressBookInterface(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void setTheScanner(Scanner testScanner) {
        this.theScanner = testScanner;
    }

    public void start(Scanner aScanner) {
        this.theScanner = aScanner;
        mainMenu();
    }

    public void mainMenu() {
        try {
            String message = """
            Please select and enter a number from the following options:
             1. Add a contact
             2. Edit a contact
             3. Delete a contact
             4. View all contacts
             5. Search for a contact
             6. Delete all contacts
            
             or 0 to exit program
            """;
            System.out.println(message);
            String option = theScanner.nextLine();
            selectedOption(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void selectedOption(String option) {
        try {
            switch(option) {
                case "1":
                    //go to add a new contact
                    goToAddNewContact();
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
                case "6":
                    //go to delete all contacts
                    gotToDeleteAllContacts();
                    break;
                case "0":
                    //closes program
                    exitProgram();
                    break;
                default:
                    //go back to main menu
                    System.out.println("Invalid option.");
                    mainMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        } catch (IllegalStateException e) {
            System.out.println("Your address book is currently empty.");
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void goToAddNewContact() {
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
            System.out.println(e.getMessage());
        }
    }

    private void addNewContact(String fName, String lName, String email, String phoneNumber) {
        try {
            Contact newContact = new Contact(fName, lName, email, phoneNumber);
            addressBook.addContact(newContact);
            System.out.println("New contact added");
            routeTheUser();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
            goToAddNewContact();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void goToEditContacts() {
        try {
            System.out.println("Enter the name of the contact you wish to edit:");
            String editContactSearch = theScanner.nextLine();
            ArrayList<Contact> contactResults = searchResults(editContactSearch);
            String contactResultName = contactResults.get(0).getName();
            editContactChoice(contactResultName, contactResults.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot find contact to edit.");
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editContactChoice(String contactName, Contact contact) {
        System.out.printf("Editing contact: %s. What field would you like to edit? %n 1. First Name %n 2. Surname %n 3. Email Address %n 4. Phone Number %n", contactName);
        String editOption = theScanner.nextLine();
        editContact(editOption, contact);
    }

    private void editContact(String option, Contact contact) {
        try {
            switch (option) {
                case "1":
                    //edit first name
                    System.out.println("Enter new first name:");
                    contact.setFirstName(theScanner.nextLine());
                    System.out.println("Contact Updated");
                    routeTheUser();
                    break;
                case "2":
                    //edit last name
                    System.out.println("Enter new surname:");
                    contact.setSurname(theScanner.nextLine());
                    System.out.println("Contact Updated");
                    routeTheUser();
                    break;
                case "3":
                    //edit email address
                    System.out.println("Enter new email address:");
                    contact.setEmail(theScanner.nextLine());
                    System.out.println("Contact Updated");
                    routeTheUser();
                    break;
                case "4":
                    //edit phone number
                    System.out.println("Enter new phone number:");
                    contact.setPhoneNumber(theScanner.nextLine());
                    System.out.println("Contact Updated");
                    routeTheUser();
                    break;
                default:
                    System.out.println("Invalid option.");
                    editContactChoice(contact.getName(), contact);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotToDeleteContact() {
        try {
            System.out.print("Enter the name of the contact you wish to delete:");
            String contactToDelete = theScanner.nextLine();
            Contact contactToDeleteSearch = searchResults(contactToDelete).get(0);
            addressBook.deleteContact(contactToDeleteSearch);
            System.out.printf("Contact %s deleted.%n", contactToDeleteSearch.getName());
            routeTheUser();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot find contact to delete.");
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotToDeleteAllContacts() {
        System.out.println("Are you sure you want to delete all contacts? (y/n)");
        String confirmation = theScanner.nextLine();
        try {
            switch (confirmation.toLowerCase()) {
                case "y":
                    deleteAllContacts();
                    break;
                case "n":
                    routeTheUser();
                    break;
                default:
                    System.out.println("Invalid option.");
                    gotToDeleteAllContacts();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAllContacts() {
        try {
            boolean isDeleted = addressBook.deleteAllContacts();
            if (isDeleted) {
                System.out.println("All contacts deleted.");
            }
            routeTheUser();
        } catch (IllegalStateException e) {
            System.out.println("Your address book is currently empty.");
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotToSearchContact() {
        try {
            System.out.println("Enter the name, email or phone number of the contact to search:");
            String searchName = theScanner.nextLine();
            ArrayList<Contact> searchResults = searchResults(searchName);
            if (searchResults.isEmpty()) {
                System.out.println("Found no matching contact");
            } else {
                System.out.println("Found matching contact/s:");
                System.out.println(searchResults);
            }
            routeTheUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Contact> searchResults(String searchTerm) {
        return addressBook.searchContacts(searchTerm);
    }

    private void routeTheUser() {
        try {
            String message = """
            What would you like to do next?
             1. Go back to main menu
             0. Exit program
            """;
            System.out.println(message);
            String option = theScanner.nextLine();
            switch (option) {
                case "1":
                    mainMenu();
                    break;
                case "0":
                    //closes program
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid option.");
                    routeTheUser();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exitProgram() {
        theScanner.close();
        System.out.println("Thank you for using DF Corp Address Book. Goodbye.");
    }
}
