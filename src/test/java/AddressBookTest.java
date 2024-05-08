import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressBookTest {
    @Nested
    @DisplayName("Address Book Tests")
    class AddressBookTests {
        private AddressBook addressBook;
        @BeforeEach
        void setUp() {
            addressBook = new AddressBook();
        }

        @AfterEach
        void tearDown() {
            addressBook = null;
        }

        @Test
        @DisplayName("should add contact to array list on creation")
        public void test() {
            //Arrange
            //Act
            addressBook.addContact("Amy", "Leung", "amy@gmail.com", "01234123123");
            //Assert
            assertEquals(1, addressBook.viewContacts().size());
        }
    }
}