public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Contact(String fName, String lName, String email, String phoneNumber) {
        if (fName.trim().isEmpty()) return;

        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.firstName.concat(lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.lastName;
    }
}
