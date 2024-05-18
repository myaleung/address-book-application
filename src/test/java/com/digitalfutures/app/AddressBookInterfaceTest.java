package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

public class AddressBookInterfaceTest {
    @Nested
    @DisplayName("Address Book Interface Tests")
    class AddressBookInterfaceTests {
        private AddressBookInterface testInterface;
        private AddressBook testAddressBook;
        private Contact testContact;
        private Scanner scanner;

        @BeforeEach
        void setUp() {
            testAddressBook = mock(AddressBook.class);
            testContact = mock(Contact.class);
            testInterface = new AddressBookInterface(testAddressBook);
            scanner = mock(Scanner.class);        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testContact = null;
            testInterface = null;
            scanner = null;
        }

        @Test
        @DisplayName("Should add contact to address book")
        public void testSelectedOptionAddNewContact() {
            //Arrange
            String fName = "John";
            String lName = "Doe";
            String email = "john.doe@example.com";
            String phoneNumber = "01234567890";

            //Act
            testInterface.addNewContact(fName, lName, email, phoneNumber);

            //Assert
            assertAll(
                () -> verify(testAddressBook, times(1)).addContact(any(Contact.class))
            );
        }

//        @Test
//        public void selectedOptionShouldDeleteContact() {
//            // Mock the Scanner inputs for deleting a contact
//            when(scanner.nextLine()).thenReturn("3", "John", "0");
//
//            // Start the interface
//            testInterface.start(scanner);
//
//            // Verify that searchContacts and deleteContact were called
//            verify(testAddressBook, times(1)).searchContacts(anyString());
//            verify(testAddressBook, times(1)).deleteContact(any(Contact.class));
//        }

//        @Test
//        @DisplayName("Should register input and edit a contact")
//        public void testRegisterInputAndEditContact() {
//            //Arrange
//            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
//            Contact testEntry2 = spy(new Contact("Millie", "Brown", "sa@me.com", "01121121222"));
//            testAddressBook.addContact(testEntry1);
//            testAddressBook.addContact(testEntry2);
//            when(scanner.nextLine()).thenReturn("2", "Molly", "1", "Changed", "0");
//            //Act
//            testInterface.start(scanner);
//            //Assert
//            assertAll(
//                    () -> assertTrue(testAddressBook.viewContacts().get(0).getName().contains("Changed"))
//            );
//        }
    }
}
