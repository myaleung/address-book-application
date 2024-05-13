package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;
import org.testng.internal.collections.Pair;

import java.lang.constant.Constable;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddressBookTest {
    @Nested
    @DisplayName("Address Book Tests")
    class AddressBookTests {
        private AddressBook testAddressBook;
        private Contact testContact;

        @BeforeEach
        void setUp() {
            testAddressBook = new AddressBook();
            testContact = mock(Contact.class);
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testContact = null;
        }

        @Test
        @DisplayName("Should add contact to array list on creation")
        public void testShouldAddContactToArrayList() {
            //Arrange
            //Act
            testAddressBook.addContact(testContact);
            //Assert
            Assertions.assertEquals(1, testAddressBook.viewContacts().size());
        }

        @Test
        @DisplayName("Should throw exception when a contact with a value of null is added")
        public void testShouldThrowExceptionWhenAddingNullContact() {
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(null));
        }
    }

    @Nested
    @DisplayName("Address Book Search Tests")
    class AddressBookSearchTests {
        private AddressBook testAddressBook;
        private Contact testEntry;

        @BeforeEach
        void setUp() {
            testAddressBook = new AddressBook();
            testEntry = spy(new Contact("Molly", "Ellis", "m@e.com", "01121121123"));
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testEntry = null;
        }

        @Test
        @DisplayName("Should return contact if name match found")
        public void testShouldReturnContactIfNameFoundInArrayList() {
            //Arrange
            String searchName = "Molly";
            testAddressBook.addContact(testEntry);
            //Act
            //Assert
            Assertions.assertEquals(testEntry, testAddressBook.searchContacts(searchName));
        }

        @Test
        @DisplayName("Should throw exception when a search value is null")
        public void testShouldThrowExceptionWhenSearchingNullValue() {
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.searchContacts(null));
        }

        @Test
        @DisplayName("Should throw exception when a search value is empty")
        public void testShouldThrowExceptionWhenSearchingEmptyValue() {
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.searchContacts(""));
        }
    }
}