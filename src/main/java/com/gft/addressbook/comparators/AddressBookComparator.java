package com.gft.addressbook.comparators;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Comparator;

public class AddressBookComparator implements Comparator<AddressBookEntry> {

    @Override
    public int compare(AddressBookEntry o1, AddressBookEntry o2) {
        int result = o1.getLastName().compareToIgnoreCase(o2.getLastName());
        if (result == 0) {
            result = o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            if (result == 0) {
                result = o1.getTelePhone().compareToIgnoreCase(o2.getTelePhone());
            }
        }
        return result;
    }
}
