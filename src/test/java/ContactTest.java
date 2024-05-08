import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            // Act
            String actualName = testContact.getName();
            // Assert
            assertEquals(expectedName, actualName);
        }

        @Test
        @DisplayName("First name is set by constructor")
        public void testFirstNameSetByConstructor() {
            //Arrange
            // Act
            String actualName = testContact.getFirstName();
            // Assert
            assertEquals(validFName, actualName);
        }

        @Test
        @DisplayName("Last name is set by constructor")
        public void testLastNameSetByConstructor() {
            //Arrange
            // Act
            String actualName = testContact.getSurname();
            // Assert
            assertEquals(validLName, actualName);
        }
    }
}
