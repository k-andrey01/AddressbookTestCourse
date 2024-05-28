package org.example.model;

public class ContactData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String email;
    private String mobilePhone;

    private String group;

    public ContactData(String firstName, String middleName, String lastName, String address, String email, String mobilePhone, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getGroup() {
        return group;
    }

}
