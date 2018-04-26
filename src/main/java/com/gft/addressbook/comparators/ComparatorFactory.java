package com.gft.addressbook.comparators;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Comparator;

public class ComparatorFactory {

    public static Comparator<AddressBookEntry> createComparator(String type){
        Comparator addressBookComparator = new AddressBookComparator();
        if (type.equalsIgnoreCase("id")) {
            addressBookComparator = new AddressBookComparatorById();
        } else if (type.equalsIgnoreCase("firstname")) {
            addressBookComparator = new AddressBookComparatorByFirstName();
        } else if (type.equalsIgnoreCase("lastname")) {
            addressBookComparator = new AddressBookComparatorByLastName();
        } else if (type.equalsIgnoreCase("telephone")) {
            addressBookComparator = new AddressBookComparatorByTelephone();
        }
        return addressBookComparator;
    }
}
