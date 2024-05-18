package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        private String fName = "John";
        private String lName = "Doe";
        private String email = "john.doe@example.com";
        private String phoneNumber = "01234567890";

        @BeforeEach
        void setUp() {
            testAddressBook = mock(AddressBook.class);
            testContact = mock(Contact.class);
            testInterface = new AddressBookInterface(testAddressBook);
            scanner = mock(Scanner.class);
        }

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
            when(scanner.nextLine()).thenReturn("1", "John", "Doe", "john.doe@example.com", "01234567890", "0");

            //Act
            testInterface.start(scanner);

            //Assert
            verify(testAddressBook, times(1)).addContact(any(Contact.class));
        }

        @Test
        @DisplayName("Should call edit contact first name once")
        public void testSelectedOptionShouldGoToEditContactFirstName() {
            // Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("Johnathan", "0");

            // Act
            testInterface.editContact("1", testEntry1);

            // Assert
            assertAll(
                    () -> assertEquals("Johnathan", testEntry1.getFirstName()),
                    () -> verify(testEntry1, times(1)).setFirstName(anyString())
            );
        }

        @Test
        @DisplayName("Should call edit contact last name once")
        public void testSelectedOptionShouldGoToEditContactLastName() {
            // Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("Evans", "0");

            // Act
            testInterface.editContact("2", testEntry1);

            // Assert
            assertAll(
                    () -> assertEquals("Evans", testEntry1.getSurname()),
                    () -> verify(testEntry1, times(1)).setSurname(anyString())
            );
        }

        @Test
        @DisplayName("Should call edit contact email once")
        public void testSelectedOptionShouldGoToEditContactEmail() {
            // Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("changed@gg.com", "0");

            // Act
            testInterface.editContact("3", testEntry1);

            // Assert
            assertAll(
                    () -> assertEquals("changed@gg.com", testEntry1.getEmail()),
                    () -> verify(testEntry1, times(1)).setEmail(anyString())
            );
        }

        @Test
        @DisplayName("Should call edit contact phone number once")
        public void testSelectedOptionShouldGoToEditContactPhoneNumber() {
            // Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("01121121126", "0");

            // Act
            testInterface.editContact("4", testEntry1);

            // Assert
            assertAll(
                    () -> assertEquals("01121121126", testEntry1.getPhoneNumber()),
                    () -> verify(testEntry1, times(1)).setPhoneNumber(anyString())
            );
        }

        @Test
        @DisplayName("Should call search contact once to check for delete")
        public void selectedOptionShouldGoToDeleteContact() {
            //Arrange
            when(scanner.nextLine()).thenReturn("3", "Molly", "0");

            //Act
            testInterface.start(scanner);

            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
            /*Unable to locate a contact to delete*/
//            verify(testAddressBook, times(1)).deleteContact(any(Contact.class));
        }

        @Test
        @DisplayName("Should call search for a contact method when viewing")
        public void selectedOptionShouldViewContacts() {
            //Arrange
            when(scanner.nextLine()).thenReturn("4", "0");
            //Act
            testInterface.start(scanner);

            //Assert
            assertAll(
                    () -> verify(testAddressBook, times(1)).viewContacts(),
                    () -> assertEquals(0, testAddressBook.searchContacts(anyString()).size())
            );
        }

        @Test
        @DisplayName("Should call search for a contact method")
        public void selectedOptionShouldSearchContacts() {
            //Arrange
            when(scanner.nextLine()).thenReturn("5", "John", "0");
            //Act
            testInterface.start(scanner);

            //Assert
            assertAll(
                    () -> verify(testAddressBook, times(1)).searchContacts(anyString()),
                    () -> assertEquals(0, testAddressBook.searchContacts(anyString()).size())
            );
        }

        @Test
        @DisplayName("Should register input and exit program")
        public void selectedOptionShouldExitProgram() {
            //Arrange
            when(scanner.nextLine()).thenReturn("0");

            //Act
            testInterface.start(scanner);

            //Assert
            verify(scanner, times(1)).close();
        }

        @Test
        @DisplayName("Should return to main menu for invalid option")
        public void selectedOptionShouldReturnToMainMenuForInvalidOption() {
            //Arrange
            when(scanner.nextLine()).thenReturn("invalid", "0");

            //Act
            testInterface.start(scanner);

            //Assert
            verify(scanner, times(2)).nextLine();
        }
    }
}
