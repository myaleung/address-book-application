import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        @DisplayName("should add contact to array list on creation")
        public void testShouldAddContactToArrayList() {
            //Arrange
            //Act
            testAddressBook.addContact("Amy", "Leung", "amy@gmail.com", "01234123123");
            //Assert
            assertEquals(1, testAddressBook.viewContacts().size());
        }

        @Test
        @DisplayName("should not add contact to array list if first name field is empty")
        public void testShouldNotAddContactToArrayListIfFirstNameFieldIsEmpty() {
            //Arrange
            //Act
            testAddressBook.addContact("", "L", "amy@gmail.com", "01234123123");
            //Assert
            assertEquals(0, testAddressBook.viewContacts().size());
        }
    }
}