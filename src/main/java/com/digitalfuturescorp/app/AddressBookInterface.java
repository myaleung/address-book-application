package com.digitalfuturescorp.app;

import java.util.Scanner;

public class AddressBookInterface {
    // Creating option integer variable
    int option = 0;

    public AddressBookInterface(AddressBook addressBook) {

    }

    public String mainMenu() {
        return """
                Welcome to your DF Corp AddressBook
                Please select from the following options:
                     1. View all contacts
                     2. Add a contact
                     3. Edit a contact
                     4. Delete a contact
                     5. Search for a contact by name
                """;
    }

    public void selectedOption(int option) {
        switch(option) {
            case 1:
                //add a new contact

                break;
            case 2:
                //delete a contact
                break;
            case 3:
                //edit a contact
                break;
            default:
                //view contacts
        }
    }

    public void printOutput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(scanner.next());
        scanner.close();
    }
}
