package com.gft.addressbook.model;

public class AddressBookEntry {
    private Integer ID;
    private String firstName;
    private String lastName;
    private String telePhone;

    @Override
    public String toString() {
        return "ID: " + ID + " " + firstName + " " + lastName + " " + telePhone;
    }

    public Integer getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public AddressBookEntry(Integer ID, String firstName, String lastName, String telePhone) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telePhone = telePhone;
    }
}