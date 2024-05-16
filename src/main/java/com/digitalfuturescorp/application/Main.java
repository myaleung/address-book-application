package com.digitalfuturescorp.application;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        AddressBookInterface dfAddressBook = new AddressBookInterface(addressBook);

        System.out.println("Welcome to your DF Corp AddressBook");
        dfAddressBook.mainMenu();
    }
}
