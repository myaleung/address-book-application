# Domain Models, Class Diagrams and Test Plan
The following are the user stories, class diagrams and tests for the Address Book software.

## Class Diagram
```mermaid
---
title: Address Book Software
---
classDiagram
    class AddressBook {
        -contacts ArrayList<Contact>
        +addContact(Contact) void
        +deleteContact(Contact) void
        +searchContact(String) Contact
        +viewContacts() ArrayList
        +deleteAllContacts() boolean
    }
    class Contact {
        -fName String
        -lName String
        -email  String
        -phoneNumber String
        +getName() String
        +getFirstName() String
        +getSurname() String
        +getEmail() String
        +getphoneNumber() String
        +setFirstName(String) void
        +setSurname(String) void
        +setEmail(String) void
        +setPhoneNumber(String) void
        -validateName(name String)$ name
        -validateEmail(email String)$ email
        -validatePhoneNumber(phoneNumber String)$ phoneNumber
    }
        
    class AddressBookInterface {
        -addressBook AddressBook 
        -theScanner Scanner
        +setTheScanner(Scanner) void
        +start(Scanner) void
        +mainMenu() void
        -selectedOption() void
        -goToViewContacts() void
        -goToAddNewContact() void
        -addNewContact() void
        -goToEditContacts() void
        +editContactChoice() void
        -editContact() void
        -goToDeleteContact() void
        -goToSearchContact() void
        -goToViewContacts() void
        -goToDeleteAllContacts() void
        -deleteAllContacts() void
        -routeTheUser() void
        -exitProgram() void
    }
```
## User Stories
<img src="images/img.png" alt="As a user, I want to be able to add a contact to the address book, so that I can keep a record of my contacts" width="450" height="100%"/>

### Tests
- [ ] addContact should +1 to array list length if contact was created.
- [ ] check email address has the correct format.
- [ ] check phone number is a string of numbers of 11 digits (UK).
---
<img src="images/img_1.png" alt="As a user, I want to only be able to add contacts if I have their first name, phone number and email address, so that I can save important information for each contact" width="450" height="100%"/>

### Tests
- [ ] addContact should only add to list if name is not null or empty.
- [ ] addContact should only add to list if email address is not null or empty.
- [ ] addContact should only add to list if telephone is not null or empty.
---
<img src="images/img_2.png" alt="As a user, I’d like to be able to search for a contact by name, so that I can see the results displayed" width="450" height="100%"/>

### Tests
- [ ] if contact that matches name is found, return contact information.
- [ ] check search entry isn't null.
- [ ] check search entry isn't empty.
---
<img src="images/img_3.png" alt="As a user, I want to be able to remove a contact from my address book so that I keep my address book tidy" width="450" height="100%"/>

### Tests
- [ ] when remove contact method is called, it should reduce the contacts length by 1.
---
<img src="images/img_4.png" alt="As a user, I want to be able to edit a contact's details so that I can change and update it" width="450" height="100%"/>

### Tests
- [ ] editContact should return all fields to update.
- [ ] After updating a contact the details should save and update.
---
<img src="images/img_5.png" alt="As a user, I'd like to not be able to add duplicate contact entries, so I don't add the same contact twice" width="450" height="100%"/>

### Tests
- [ ] addContact should throw an error if email address already exists in contacts.
- [ ] addContact should throw an error if phone number already exists in contacts.
---
<img src="images/img_6.png" alt="As a user, I'd like to be able to see all my contacts in my address book so I can see how many contacts I have" width="450" height="100%"/>

### Tests
- [ ] viewContacts should show all results in the address book and match the length in the array
- [ ] viewContacts should show results in alphabetical order by firstname
---
<img src="images/img_7.png" alt="As a user, I want to be able to use a console interface for the address book so that I can interact with the application" width="450" height="100%"/>

### Tests
- [ ] inputs should be registered.
- [ ] scanned input should be able to add new contact.
- [ ] scanned input should be able to edit an existing contact.
- [ ] scanned input should be able to delete a contact.
- [ ] inputs should not be null.
- [ ] inputs should not be empty.
---
<img src="images/img_8.png" alt="As a user,I want to be able to search a contact by phone number, so that I see contacts that match my search" width="450" height="100%"/>

### Tests
- [ ] phone number should be able to be entered as a search term
- [ ] should return contact information if phone number matches
- [ ] should throw an error if no contact matches the entered phone number
---
<img src="images/img_9.png" alt="As a user, I want to be able to search a contact by email address, so that I see contacts that match my search" width="450" height="100%"/>

### Tests
- [ ] email address should be able to be entered as a search term
- [ ] should return contact information if email address matches
- [ ] should throw an error if no contact matches the entered email address
---
<img src="images/img_10.png" alt="As a user, I want to be able to partially search a contact by name, phone number or email address, so that I see contacts that match my search" width="450" height="100%"/>

### Tests
- [ ] search should return results that partially match any values by name
- [ ] search should return results that partially match any values by phone number
- [ ] search should return results that partially match any values by email address
---
<img src="images/img_11.png" alt="As a user, I want to be able to delete all my contacts, so that I can clean and clear my address book" width="450" height="100%"/>

### Tests
- [ ] deleteAllContacts should remove all contacts from the address book
- [ ] should return true if contacts are deleted
- [ ] should throw an error if no contacts are found

## Kanban Board
I used a kanban board on Miro to help organise my user stories and production tickets. Here I also included my domain models\
See Miro Board: https://miro.com/app/board/uXjVKKvlnHA=/?share_link_id=781789857612
![Kanban User Stories List](images/backlog.png)
