package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
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

        @Test
        @DisplayName("Should throw exception when a contact with the same email address exists in contacts")
        public void testShouldThrowExceptionWhenSameEmailFoundInContactsList() {
            //Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            Contact testEntry2 = spy(new Contact("Millie", "Brown", "sa@me.com", "01121121222"));

            //Act
            testAddressBook.addContact(testEntry1);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testEntry2));
        }

        @Test
        @DisplayName("Should throw exception when a contact with the same phone number exists in contacts")
        public void testShouldThrowExceptionWhenSamePhoneNumberFoundInContactsList() {
            //Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "m@e.com", "01121121222"));
            Contact testEntry2 = spy(new Contact("Millie", "Brown", "another@e.com", "01121121222"));
            //Act
            testAddressBook.addContact(testEntry1);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testEntry2));
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

        @Test
        @DisplayName("Should return contact if partial match found")
        public void testShouldReturnContactIfPartialMatchFoundInArrayList() {
            //Arrange
            String searchName = "Mol";
            testAddressBook.addContact(testEntry);
            //Act
            //Assert
            Assertions.assertEquals(testEntry, testAddressBook.searchContacts(searchName));
        }

        @Test
        @DisplayName("Should return null if there isn't a match")
        public void testWhenNoResultsFound() {
            //Arrange
            String searchName = "Mollie";
            testAddressBook.addContact(testEntry);
            //Act
            //Assert
            Assertions.assertNull(testAddressBook.searchContacts(searchName));
        }
    }

    @Nested
    @DisplayName("Address Book Contact Mutation Tests")
    class AddressBookContactMutationTests {
        private AddressBook testAddressBook;
        private Contact testEntry1;
        private Contact testEntry2;

        @BeforeEach
        void setUp() {
            testAddressBook = new AddressBook();
            testEntry1 = spy(new Contact("Molly", "Ellis", "m@e.com", "01121121123"));
            testEntry2 = spy(new Contact("Peter", "Ellis", "pe@f.com", "01188121123"));
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testEntry1 = null;
        }

        @Test
        @DisplayName("Should remove contact from contacts list")
        public void testShouldDeleteContactFromArrayList() {
            //Arrange
            testAddressBook.addContact(testEntry1);
            testAddressBook.addContact(testEntry2);
            //Act
            testAddressBook.deleteContact(testEntry2);
            //Assert
            assertAll(
                    () -> assertEquals(1,testAddressBook.viewContacts().size()),
                    () -> assertFalse(testAddressBook.viewContacts().contains(testEntry2))
            );
        }
    }
}