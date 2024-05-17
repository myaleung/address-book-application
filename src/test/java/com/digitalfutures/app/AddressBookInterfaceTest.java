package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        }

//        @Test
//        @DisplayName("Should add contact to address book")
//        public void testAddContactToAddressBook() {
////            testInterface.addNewContact(scanner);
////            when(testAddressBook.addContact()).thenReturn()
//            testInterface.start(scanner);
//            when(scanner.nextLine()).thenReturn("1","Amy", "Lee", "aa@aa.com", "01111111111", "0");
////            when(testAddressBook.viewContacts().size()).thenReturn(1);
//            testAddressBook.addContact(testContact);
//            assertAll(
//                    () -> assertEquals(1, testAddressBook.viewContacts().size())
//            );
//        }

//        @Test
//        @DisplayName("Should register input and show contacts")
//        public void testRegisterInputAndShowContacts() {
//            when(scanner.nextLine()).thenReturn("4", "0");
//            testInterface.start(scanner);
//            assertAll(
//                    () -> assertTrue(testAddressBook.viewContacts().isEmpty())
//            );
//        }
    }
}
