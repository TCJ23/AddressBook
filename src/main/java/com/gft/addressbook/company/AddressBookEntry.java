package com.gft.addressbook.company;

public class AddressBookEntry {
    private Integer ID;
    private String firstName;
    private String lastName;
    private String telePhone;

    @Override
    public String toString() {
        return "ID: "+ID + " " + firstName + " " + lastName + " " + telePhone;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if ()
//    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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