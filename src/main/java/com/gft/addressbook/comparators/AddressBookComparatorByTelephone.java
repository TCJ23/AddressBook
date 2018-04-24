package com.gft.addressbook.comparators;

import com.gft.addressbook.company.AddressBookEntry;

import java.util.Comparator;

public class AddressBookComparatorByTelephone implements Comparator<AddressBookEntry> {
    @Override
    public int compare(AddressBookEntry o1, AddressBookEntry o2) {
        int result = o1.getTelePhone().compareToIgnoreCase(o2.getTelePhone());
        return result;
    }
}
