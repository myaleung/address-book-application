package com.digitalfutures.app;

import com.digitalfuturescorp.app.AddressBook;
import com.digitalfuturescorp.app.AddressBookInterface;
import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

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

        @BeforeEach
        void setUp() {
            testAddressBook = mock(AddressBook.class);
            testContact = mock(Contact.class);
            testInterface = new AddressBookInterface(testAddressBook);
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testContact = null;
            testInterface = null;
        }

        @Test
        @DisplayName("Should register input and show contacts")
        public void testRegisterInputAndShowContacts() {
            Scanner scanner = mock(Scanner.class);
            when(scanner.nextLine()).thenReturn("1");
            testInterface.start(scanner);
            assertAll(
                    () -> assertEquals("1", scanner.nextLine()),
                    () -> assertTrue(testAddressBook.viewContacts().isEmpty())
            );
        }

//        @Test
//        @DisplayName("Should register input and show correct menu")
//        public void testInputAndShowCorrespondingMenu() {
//            //Arrange
//            final int testOption = KeyEvent.VK_1;
//            //Act
//            testInterface.selectedOption(testOption);
//            //Assert
//            assertAll(
//                    () -> assertEquals(2, testAddressBook.viewContacts().size())
//            );
//        }
    }
}
