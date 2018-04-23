package comparators;

import company.AddressBookEntry;

import java.util.Comparator;

public class AddressBookComparatorByLastName implements Comparator<AddressBookEntry> {
    @Override
    public int compare(AddressBookEntry o1, AddressBookEntry o2) {
        int result = o1.getLastName().compareToIgnoreCase(o2.getLastName());
        return result;
    }
}


