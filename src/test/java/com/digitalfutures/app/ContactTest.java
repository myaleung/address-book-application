package com.digitalfutures.app;

import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {
    private Contact testContact;
    private final String validFName = "Amy";
    private final String validLName = "Leung";
    private final String validEmail = "email@email.com";
    private final String validPhoneNumber = "01234123123";

    @BeforeEach
    void setUp() {
        testContact = new Contact(validFName, validLName, validEmail, validPhoneNumber);
    }

    @AfterEach
    void tearDown() {
        testContact = null;
    }

    @Nested
    @DisplayName("Contact Tests")
    class ContactTests {
        @Test
        @DisplayName("Name is set by constructor")
        public void testNameSetByConstructor() {
            //Arrange
            String expectedName = validFName + validLName;
            //Act
            String actualName = testContact.getName();
            //Assert
            assertEquals(expectedName, actualName);
        }

        @Test
        @DisplayName("First name is set by constructor")
        public void testFirstNameSetByConstructor() {
            //Arrange
            //Act
            String actualName = testContact.getFirstName();
            //Assert
            assertEquals(validFName, actualName);
        }

        @Test
        @DisplayName("Last name is set by constructor")
        public void testLastNameSetByConstructor() {
            //Arrange
            //Act
            String actualName = testContact.getSurname();
            //Assert
            assertEquals(validLName, actualName);
        }

        @Test
        @DisplayName("Throw Exception when first name field is empty")
        public void testConstructorThrowsExceptionWhenFirstNameIsEmpty() {
            //Arrange
            String testFName = "";
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(testFName, validLName, validEmail, validPhoneNumber));
        }

        @Test
        @DisplayName("Throw Exception when first name field is null")
        public void testConstructorThrowsExceptionWhenFirstNameIsNull() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(null, validLName, validEmail, validPhoneNumber));
        }

        @Test
        @DisplayName("Throw Exception when last name field is empty")
        public void testConstructorThrowsExceptionWhenLastNameIsEmpty() {
            //Arrange
            String testLName = "";
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, testLName, validEmail, validPhoneNumber));
        }

        @Test
        @DisplayName("Throw Exception when last name field is null")
        public void testConstructorThrowsExceptionWhenLastNameIsNull() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, null, validEmail, validPhoneNumber));
        }

        @Test
        @DisplayName("Email is set by constructor")
        public void testEmailSetByConstructor() {
            //Arrange
            //Act
            String actualEmail = testContact.getEmail();
            //Assert
            assertEquals(validEmail, actualEmail);
        }

        @Test
        @DisplayName("Throw Exception when email field is empty")
        public void testConstructorThrowsExceptionWhenEmailIsEmpty() {
            //Arrange
            String testEmail = "";
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, testEmail, validPhoneNumber));
        }

        @Test
        @DisplayName("Throw Exception when email field is null")
        public void testConstructorThrowsExceptionWhenEmailIsNull() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, null, validPhoneNumber));
        }

        @Test
        @DisplayName("Phone number is set by constructor")
        public void testPhoneNumberSetByConstructor() {
            //Arrange
            //Act
            String actualPhoneNumber = testContact.getPhoneNumber();
            //Assert
            assertEquals(validPhoneNumber, actualPhoneNumber);
        }

        @Test
        @DisplayName("Throw Exception when phone number field is empty")
        public void testConstructorThrowsExceptionWhenPhoneNumberIsEmpty() {
            //Arrange
            String testPhoneNumber = "";
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, validEmail, testPhoneNumber));
        }

        @Test
        @DisplayName("Throw Exception when phone number field is null")
        public void testConstructorThrowsExceptionWhenPhoneNumberIsNull() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, validEmail, null));
        }

        @Test
        @DisplayName("Throw Exception when phone number field is not 11 digits long")
        public void testConstructorThrowsExceptionWhenPhoneNumberIsNot11Digits() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, validEmail, "121121"));
        }

        @Test
        @DisplayName("Throw Exception when email address field does not have the correct format")
        public void testConstructorThrowsExceptionWhenEmailAddressIsNotInCorrectFormat() {
            //Arrange
            //Act
            //Assert
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact(validFName, validLName, "e.@", validPhoneNumber));
        }
    }


}
