package com.digitalfutures.app;

import com.digitalfuturescorp.app.Contact;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    private Contact testContact;
    private Contact testContact2;
    private final String validFName = "Amy";
    private final String validLName = "Leung";
    private final String validEmail = "email@email.com";
    private final String validPhoneNumber = "01234123123";
    private String testFName = "Tess";
    private String testLName = "Change";
    private String testEmail = "tess@domain.com";
    private String testPhoneNumber = "01222321321";

    @BeforeEach
    void setUp() {
        testContact = new Contact(validFName, validLName, validEmail, validPhoneNumber);
        testContact2 = new Contact(testFName, testLName, testEmail, testPhoneNumber);
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
            String expectedName = validFName + " " + validLName;
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

    @Nested
    @DisplayName("Contact Manipulation Tests")
    class ContactManipulationTests {
        @Test
        @DisplayName("Should change contact entry first name")
        public void testContactFirstNameIsChanged() {
            //Arrange
            String testName = "Tee";
            String oldName = testContact2.getFirstName();
            //Act
            testContact2.setFirstName(testName);
            String actualName = testContact2.getFirstName();

            //Assert
            assertAll(
                    () -> assertEquals(testName, actualName),
                    () -> assertNotEquals(oldName, actualName)
            );
        }

        @Test
        @DisplayName("Should change contact entry last name")
        public void testContactLastNameIsChanged() {
            //Arrange
            String testName = "Changed";
            String oldName = testContact2.getSurname();
            //Act
            testContact2.setSurname(testName);
            String actualName = testContact2.getSurname();

            //Assert
            assertAll(
                    () -> assertEquals(testName, actualName),
                    () -> assertNotEquals(oldName, actualName)
            );
        }

        @Test
        @DisplayName("Should change contact entry email address")
        public void testContactEmailAddressIsChanged() {
            //Arrange
            String newEmail = "this@newemail.co";
            String oldEmail = testContact2.getEmail();
            //Act
            testContact2.setEmail(newEmail);
            String actualName = testContact2.getEmail();

            //Assert
            assertAll(
                    () -> assertEquals(newEmail, actualName),
                    () -> assertNotEquals(oldEmail, actualName)
            );
        }

        @Test
        @DisplayName("Should change contact entry phone number")
        public void testContactPhoneNumberIsChanged() {
            //Arrange
            String newPhoneNumber = "01155654654";
            String oldPhoneNumber = testContact2.getPhoneNumber();
            //Act
            testContact2.setPhoneNumber(newPhoneNumber);
            String actualName = testContact2.getPhoneNumber();

            //Assert
            assertAll(
                    () -> assertEquals(newPhoneNumber, actualName),
                    () -> assertNotEquals(oldPhoneNumber, actualName)
            );
        }
    }
}
