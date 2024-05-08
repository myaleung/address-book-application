package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressBookTest {
    @Nested
    @DisplayName("Address Book Tests")
    class AddressBookTests {
        private AddressBook testAddressBook;

        @BeforeEach
        void setUp() {
            testAddressBook = new AddressBook();
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
        }

        @Test
        @DisplayName("Should add contact to array list on creation")
        public void testShouldAddContactToArrayList() {
            //Arrange
            //Act
            testAddressBook.addContact("Amy", "Leung", "amy@gmail.com", "01234123123");
            //Assert
            Assertions.assertEquals(1, testAddressBook.viewContacts().size());
        }

        @Test
        @DisplayName("Should throw exception if first name field is empty")
        public void testShouldThrowExceptionIfFirstNameFieldIsEmpty() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> testAddressBook.addContact("", "L", "amy@gmail.com", "01234123123"));
        }
    }
}