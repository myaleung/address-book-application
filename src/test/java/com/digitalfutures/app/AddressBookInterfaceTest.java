package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AddressBookInterfaceTest {
    @Nested
    @DisplayName("Address Book Interface Tests")
    class AddressBookInterfaceTests {
        private final InputStream systemIn = System.in;
        private final PrintStream systemOut = System.out;

        private ByteArrayInputStream testIn;
        private ByteArrayOutputStream testOut;
        private AddressBookInterface testInterface;
        private AddressBook testAddressBook;
        private Contact testContact;

        @BeforeEach
        void setUp() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
            testAddressBook = mock(AddressBook.class);
            testContact = mock(Contact.class);
            testInterface = new AddressBookInterface(testAddressBook);
        }

        void provideInput(String data) {
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @AfterEach
        void tearDown() {
            System.setIn(systemIn);
            System.setOut(systemOut);
            testAddressBook = null;
            testContact = null;
            testInterface = null;
        }

        @Test
        @DisplayName("Should show options available on main menu")
        public void testMainMenuOptionsAreShown() {
            //Arrange
            //Act
            //Assert
            assertEquals("""
                Welcome to your DF Corp AddressBook
                Please select from the following options:
                     1. View all contacts
                     2. Add a contact
                     3. Edit a contact
                     4. Delete a contact
                     5. Search for a contact by name
                """, testInterface.mainMenu());
        }

        @Test
        @DisplayName("Should print out the same information as input")
        public void testSystemInCollectsAndPrintsSameString() {
            //Arrange
            final String testFirstName = "Mel";
            final String testLastName = "Banks";
            final String testEmail = "mel@gmail.com";
            final String testPhoneNumber = "033333344455";
            //Act
            provideInput(testFirstName);
//            provideInput(testLastName);
//            provideInput(testEmail);
//            provideInput(testPhoneNumber);
            testInterface.printOutput();
            //Assert
            assertEquals(testFirstName, getOutput());
        }
    }
}
