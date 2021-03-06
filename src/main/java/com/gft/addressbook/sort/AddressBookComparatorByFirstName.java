package com.gft.addressbook.sort;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Comparator;

public class AddressBookComparatorByFirstName implements Comparator<AddressBookEntry> {

    @Override
    public int compare(AddressBookEntry o1, AddressBookEntry o2) {
        int result = o1.getFirstName().compareToIgnoreCase(o2.getFirstName());

        return result;
    }
}
