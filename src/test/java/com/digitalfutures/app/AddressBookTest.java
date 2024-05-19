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
            assertEquals(1, testAddressBook.viewContacts().size());
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

        @Test
        @DisplayName("Should show all contacts in the address book")
        public void testShouldShowAllContactsInArrayList() {
            //Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "m@e.com", "01121121222"));
            Contact testEntry2 = spy(new Contact("Millie", "Brown", "another@e.com", "01121121223"));
            testAddressBook.addContact(testEntry1);
            testAddressBook.addContact(testEntry2);
            //Act
            String actualEmail = testAddressBook.viewContacts().get(0).getEmail();
            //Assert
            assertAll(
                () -> assertEquals(2, testAddressBook.viewContacts().size()),
                () -> assertEquals(testEntry2.getEmail(), actualEmail)
            );
        }

        @Test
        @DisplayName("Should show all contacts from address book in alphabetical order")
        public void testShouldShowAllContactsInAlphabeticalOrder() {
            //Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "m@e.com", "01121121222"));
            Contact testEntry2 = spy(new Contact("Millie", "Brown", "another@e.com", "01121121223"));
            Contact testEntry3 = spy(new Contact("Josh", "Su", "joshsu@email.com", "07054538974"));
            Contact testEntry4 = spy(new Contact("Toni", "Miller", "nini@email.com", "07441534951"));
            Contact testEntry5 = spy(new Contact("Sam", "Lotte", "slotte@web.co.uk", "09427434606"));
            testAddressBook.addContact(testEntry1);
            testAddressBook.addContact(testEntry2);
            testAddressBook.addContact(testEntry3);
            testAddressBook.addContact(testEntry4);
            testAddressBook.addContact(testEntry5);
            //Act
            String actualFirstResult = testAddressBook.viewContacts().get(0).getName();
            String actualSecondResult = testAddressBook.viewContacts().get(1).getFirstName();
            String actualLastResult = testAddressBook.viewContacts().get(testAddressBook.viewContacts().size() - 1).getName();
            //Assert
            assertAll(
                () -> assertEquals("Josh Su", actualFirstResult),
                () -> assertEquals("Millie", actualSecondResult),
                () -> assertEquals("Toni Miller", actualLastResult)
            );
        }

        @Test
        @DisplayName("Should throw exception when trying to view an empty address book")
        public void testThrowExceptionWhenViewingEmptyAddressBook() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalStateException.class, ()->testAddressBook.viewContacts());
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
            assertTrue(testAddressBook.searchContacts(searchName).contains(testEntry));
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
            assertTrue(testAddressBook.searchContacts(searchName).contains(testEntry));
        }

        @Test
        @DisplayName("Should return null if there isn't a match")
        public void testWhenNoResultsFound() {
            //Arrange
            String searchName = "Mollie";
            testAddressBook.addContact(testEntry);
            //Act
            //Assert
            assertAll(
                    () -> assertFalse(testAddressBook.searchContacts(searchName).contains(testEntry)),
                    () -> assertTrue(testAddressBook.searchContacts(searchName).isEmpty()),
                    () -> assertEquals(0, testAddressBook.searchContacts(searchName).size())
            );
        }

        @Test
        @DisplayName("Should return contact if email match found")
        public void testShouldReturnContactIfEmailFoundInArrayList() {
            //Arrange
            String searchEmail = "m@e.com";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchEmail);
            //Assert
            assertTrue(testAddressBook.searchContacts(searchEmail).contains(testEntry));
        }

        @Test
        @DisplayName("Should return contact if partial email match found")
        public void testShouldReturnContactIfPartialEmailFoundInArrayList() {
            //Arrange
            String searchEmail = "@e.com";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchEmail);
            //Assert
            assertTrue(testAddressBook.searchContacts(searchEmail).contains(testEntry));
        }

        @Test
        @DisplayName("Should return empty list if email match not found")
        public void testShouldReturnEmptyListIfEmailNotFoundInArrayList() {
            //Arrange
            String searchEmail = "e@e.com";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchEmail);
            //Assert
            assertAll(
                    () -> assertFalse(testAddressBook.searchContacts(searchEmail).contains(testEntry)),
                    () -> assertEquals(0, testAddressBook.searchContacts(searchEmail).size())
            );
        }

        @Test
        @DisplayName("Should return contact if phone number match found")
        public void testShouldReturnContactIfPhoneNumberFoundInArrayList() {
            //Arrange
            String searchPhoneNo = "01121121123";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchPhoneNo);
            //Assert
            assertTrue(testAddressBook.searchContacts(searchPhoneNo).contains(testEntry));
        }

        @Test
        @DisplayName("Should return contact if partial phone number match found")
        public void testShouldReturnContactIfPartialPhoneNumberFoundInArrayList() {
            //Arrange
            String searchPhoneNo = "121123";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchPhoneNo);
            //Assert
            assertTrue(testAddressBook.searchContacts(searchPhoneNo).contains(testEntry));
        }

        @Test
        @DisplayName("Should return empty list if phone number match not found")
        public void testShouldReturnEmptyListIfPhoneNumberNotFoundInArrayList() {
            //Arrange
            String searchPhoneNo = "01121121166";
            testAddressBook.addContact(testEntry);
            //Act
            testAddressBook.searchContacts(searchPhoneNo);
            //Assert
            assertAll(
                    () -> assertFalse(testAddressBook.searchContacts(searchPhoneNo).contains(testEntry)),
                    () -> assertEquals(0, testAddressBook.searchContacts(searchPhoneNo).size())
            );
        }

        @Test
        @DisplayName("Should throw error is invalid characters are in search term")
        public void testShouldThrowExceptionIfInvalidCharactersUsedInSearchTerm() {
            //Arrange
            String searchTerm = "01were%%??1123";
            testAddressBook.addContact(testEntry);
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class, ()-> testAddressBook.searchContacts(searchTerm));
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

        @Test
        @DisplayName("Should not delete a non-existing contact")
        public void testShouldNotDeleteANonExistingContact() {
            //Arrange
            testAddressBook.addContact(testEntry1);
            //Act
            //Assert
            assertAll(
                    () -> assertEquals(1,testAddressBook.viewContacts().size()),
                    () -> assertThrows(IllegalArgumentException.class, ()->testAddressBook.deleteContact(null)),
                    () -> assertThrows(IllegalArgumentException.class, ()->testAddressBook.deleteContact(testEntry2))
            );
        }

        @Test
        @DisplayName("Should throw exception when trying to delete an empty contact list")
        public void testShouldThrowExceptionWhenDeletingEmptyContactList() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalStateException.class, ()->testAddressBook.deleteAllContacts());
        }

        @Test
        @DisplayName("Should remove all contacts from contacts list")
        public void testShouldRemoveAllContactsFromContactList() {
            //Arrange
            testAddressBook.addContact(testEntry1);
            testAddressBook.addContact(testEntry2);
            //Act
            boolean result = testAddressBook.deleteAllContacts();
            //Assert
            assertTrue(result);
        }
    }
}