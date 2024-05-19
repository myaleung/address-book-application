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
        private ArrayList<Contact> mockArrayList;
        private Scanner scanner;

        @BeforeEach
        void setUp() {
            testAddressBook = mock(AddressBook.class);
            testContact = mock(Contact.class);
            testInterface = new AddressBookInterface(testAddressBook);
            mockArrayList = mock(ArrayList.class);
            scanner = mock(Scanner.class);
        }

        @AfterEach
        void tearDown() {
            testAddressBook = null;
            testContact = null;
            testInterface = null;
            mockArrayList = null;
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
        @DisplayName("Should handle invalid contact details")
        public void goToAddNewContactShouldHandleInvalidDetails() {
            //Arrange
            when(scanner.nextLine()).thenReturn("1", "",  "", "", "", "Tess", "Tester", "test@test.com", "01234123123", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).addContact(any(Contact.class));
        }

        @Test
        @DisplayName("Should go to goToEditContacts method")
        public void selectedOptionShouldGoToEditContacts() {
            //Arrange
            when(scanner.nextLine()).thenReturn("2", "John", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
        }

        @Test
        @DisplayName("Should go to goToEditContacts method and edit contact when valid contact is found")
        public void goToEditContactsShouldEditContact() {
            //Arrange
            when(scanner.nextLine()).thenReturn("2","Mock Contact", "1", "New Name", "0");
            when(mockArrayList.get(0)).thenReturn(testContact);
            when(testAddressBook.searchContacts(anyString())).thenReturn(mockArrayList);
            mockArrayList.add(testContact);
            when(testContact.getName()).thenReturn("Mock Contact");
            //Act
            testInterface.start(scanner);
            //Assert
            assertAll(
                    () -> verify(testContact, times(1)).setFirstName(anyString()),
                    () -> verify(testAddressBook, times(1)).searchContacts(anyString())
            );
        }

        @Test
        @DisplayName("Should go to editContactChoice method")
        public void selectedOptionShouldGoToEditContactChoice() {
            //Arrange
            when(scanner.nextLine()).thenReturn("2", "John", "2", "0");
            when(mockArrayList.get(0)).thenReturn(testContact);
            when(mockArrayList.get(0).getName()).thenReturn("John Doe");
            //Act
            testInterface.start(scanner);
            //Assert
            assertAll(
                    () -> verify(testAddressBook, times(1)).searchContacts(anyString()),
                    () -> assertEquals("John Doe", testContact.getName()),
                    () -> verify(testContact, times(1)).getName()
            );
        }

        @Test
        @DisplayName("Should call edit contact first name once")
        public void testSelectedOptionShouldGoToEditContactFirstName() {
            // Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("1","Johnathan", "0");
            // Act
            testInterface.editContactChoice(testEntry1.getName(), testEntry1);
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
            when(scanner.nextLine()).thenReturn("2","Evans", "0");
            // Act
            testInterface.editContactChoice(testEntry1.getName(), testEntry1);
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
            when(scanner.nextLine()).thenReturn("3", "changed@gg.com", "0");
            // Act
            testInterface.editContactChoice(testEntry1.getName(), testEntry1);
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
            when(scanner.nextLine()).thenReturn("4","01121121126", "0");
            // Act
            testInterface.editContactChoice(testEntry1.getName(), testEntry1);
            // Assert
            assertAll(
                    () -> assertEquals("01121121126", testEntry1.getPhoneNumber()),
                    () -> verify(testEntry1, times(1)).setPhoneNumber(anyString())
            );
        }

        @Test
        @DisplayName("Should handle non-existent contact for editing")
        public void goToEditContactsShouldHandleNonExistentContact() {
            //Arrange
            when(scanner.nextLine()).thenReturn("2", "NonExistent", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
        }

        @Test
        @DisplayName("Should handle invalid option")
        public void editContactChoiceShouldHandleInvalidOption() {
            //Arrange
            Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
            testInterface.setTheScanner(scanner);
            when(scanner.nextLine()).thenReturn("invalid", "1", "Mills", "0");
            //Act
            testInterface.editContactChoice(testEntry1.getName(), testEntry1);
            //Assert
            assertAll(
                    () -> assertEquals("Mills", testEntry1.getFirstName()),
                    () -> verify(testEntry1, times(1)).setFirstName(anyString())
            );
        }

        @Test
        @DisplayName("Should call search contact once to check for delete")
        public void selectedOptionShouldGoToDeleteContact() {
            //Arrange
            when(scanner.nextLine()).thenReturn("3", "Molly", "0");
            when(mockArrayList.get(0)).thenReturn(testContact);
            when(testAddressBook.searchContacts(anyString())).thenReturn(mockArrayList);
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
            verify(testAddressBook, times(1)).deleteContact(any(Contact.class));
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
        @DisplayName("Should display all contacts when address book is not empty")
        public void goToViewContactsShouldDisplayWhenNotEmpty() {
            // Arrange
            mockArrayList.add(new Contact("John", "Doe", "john.doe@example.com", "01234567890"));
            when(scanner.nextLine()).thenReturn("4", "0");
            when(testAddressBook.viewContacts()).thenReturn(mockArrayList);
            // Act
            testInterface.start(scanner);
            // Assert
            verify(testAddressBook, times(1)).viewContacts();
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
        @DisplayName("Should search for a contact and find results")
        public void gotToSearchContactShouldFindResults() {
            //Arrange
            when(scanner.nextLine()).thenReturn("5","Mock", "0");
            mockArrayList.add(testContact);
            when(testAddressBook.searchContacts(anyString())).thenReturn(mockArrayList);
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
            verify(scanner, times(3)).nextLine();
        }

        @Test
        @DisplayName("Should handle non-existent contact for search")
        public void gotToSearchContactShouldHandleNonExistentContact() {
            //Arrange
            when(scanner.nextLine()).thenReturn("5", "NonExistent", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
        }

        @Test
        @DisplayName("Should handle exception when searching for a contact")
        public void gotToSearchContactShouldHandleException() {
            //Arrange
            when(scanner.nextLine()).thenReturn("5","Mock Contact", "0");
            when(testAddressBook.searchContacts(anyString())).thenThrow(new RuntimeException("Mock Exception"));
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).searchContacts(anyString());
            verify(scanner, times(2)).nextLine();
        }

        @Test
        @DisplayName("Should call delete all contacts when address book is not empty")
        public void gotToDeleteAllContactsShouldDeleteWhenNotEmpty() {
            //Arrange
            when(testAddressBook.deleteAllContacts()).thenReturn(true);
            when(scanner.nextLine()).thenReturn("6", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(testAddressBook, times(1)).deleteAllContacts();
        }

        @Test
        @DisplayName("Should not delete any contacts when address book is empty")
        public void gotToDeleteAllContactsShouldNotDeleteWhenEmpty() {
            // Arrange
            when(testAddressBook.deleteAllContacts()).thenReturn(false);
            when(scanner.nextLine()).thenReturn("6", "0");
            // Act
            testInterface.start(scanner);
            // Assert
            verify(testAddressBook, times(1)).deleteAllContacts();
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
        @DisplayName("Should return to main menu when option 1 is selected")
        public void routeTheUserShouldReturnToMainMenu() {
            //Arrange
            when(scanner.nextLine()).thenReturn("4","1", "0");
            //Act
            testInterface.start(scanner);
            //Assert
            verify(scanner, times(1)).close();
        }

        @Test
        @DisplayName("Should handle invalid option and return to route menu")
        public void routeTheUserShouldReturnToRouteMenu() {
            //Arrange
            when(scanner.nextLine()).thenReturn("4","invalid", "0");
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

        @Nested
        @DisplayName("Address Book Interface Edge Cases")
        public class AddressBookInterfaceEdgeCases {
            @Test
            @DisplayName("Should handle invalid option in selectedOption method")
            public void selectedOptionShouldHandleInvalidOption() {
                // Arrange
                when(scanner.nextLine()).thenReturn("invalid", "0");
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(2)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in goToAddNewContact method")
            public void goToAddNewContactShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("1").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(2)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in addNewContact method")
            public void addNewContactShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("1", "John", "Doe", "john.doe@example.com").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(5)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in goToEditContacts method")
            public void goToEditContactsShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("2", "John").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(3)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in editContact method")
            public void editContactShouldHandleException() {
                // Arrange
                Contact testEntry1 = spy(new Contact("Molly", "Ellis", "sa@me.com", "01121121123"));
                testInterface.setTheScanner(scanner);
                when(scanner.nextLine()).thenReturn("1").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.editContactChoice(testEntry1.getName(), testEntry1);
                // Assert
                verify(scanner, times(2)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in selectedOption method")
            public void selectedOptionShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(1)).nextLine();
            }

            @Test
            @DisplayName("Should handle empty address book in goToViewContacts method")
            public void goToViewContactsShouldHandleEmptyAddressBook() {
                // Arrange
                when(scanner.nextLine()).thenReturn("4", "0");
                when(testAddressBook.viewContacts()).thenThrow(new IllegalStateException());
                // Act
                testInterface.start(scanner);
                // Assert
                verify(testAddressBook, times(1)).viewContacts();
            }

            @Test
            @DisplayName("Should handle exception in gotToSearchContact method")
            public void gotToSearchContactShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("5", "John").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(3)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in gotToDeleteContact method")
            public void gotToDeleteContactShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("3", "John").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(3)).nextLine();
            }

            @Test
            @DisplayName("Should handle exception in gotToDeleteAllContacts method")
            public void gotToDeleteAllContactsShouldHandleException() {
                // Arrange
                when(testAddressBook.deleteAllContacts()).thenThrow(new RuntimeException("Mock Exception"));
                when(scanner.nextLine()).thenReturn("6", "0");
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(1)).nextLine();
                verify(testAddressBook, times(1)).deleteAllContacts();
            }

            @Test
            @DisplayName("Should handle exception in routeTheUser method")
            public void routeTheUserShouldHandleException() {
                // Arrange
                when(scanner.nextLine()).thenReturn("4", "1").thenThrow(new RuntimeException("Mock Exception"));
                // Act
                testInterface.start(scanner);
                // Assert
                verify(scanner, times(3)).nextLine();
            }
        }
    }
}
