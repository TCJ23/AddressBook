package com.gft.addressbook.comparators;


import com.gft.addressbook.company.AddressBookEntry;

import java.util.Comparator;

public class AddressBookComparatorById implements Comparator<AddressBookEntry> {

    @Override
    public int compare(AddressBookEntry o1, AddressBookEntry o2) {
        int result = o1.getID().compareTo(o2.getID());
        return result;
    }
}
