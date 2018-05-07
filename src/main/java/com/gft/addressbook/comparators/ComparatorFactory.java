package com.gft.addressbook.comparators;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Comparator;

public class ComparatorFactory {

    public static Comparator<AddressBookEntry> createComparator(String type) throws WrongSortTypeException {

//        Comparator addressBookComparator = new AddressBookComparator();
        if (type.equalsIgnoreCase("id")) {
            return  new AddressBookComparatorById();
        } else if (type.equalsIgnoreCase("firstname")) {
            return new AddressBookComparatorByFirstName();
        } else if (type.equalsIgnoreCase("lastname")) {
            return new AddressBookComparatorByLastName();
        } else if (type.equalsIgnoreCase("telephone")) {
           return new AddressBookComparatorByTelephone();
        } else {
            throw new WrongSortTypeException(type);
        }
//        return addressBookComparator;
    }
}