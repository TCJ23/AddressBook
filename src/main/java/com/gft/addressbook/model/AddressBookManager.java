package com.gft.addressbook.model;

import com.gft.addressbook.ConsoleInput;
import com.gft.addressbook.CsvImporter;
import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.comparators.AddressBookComparator;
import com.gft.addressbook.comparators.ComparatorFactory;
import com.gft.addressbook.comparators.WrongSortTypeException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Component
public class AddressBookManager implements IAddressBookManager {
    private List<AddressBookEntry> mainListOfAddressBookEntries = new ArrayList<>();
    private Integer counter = 0;
    ConsoleInput consoleInput = new ConsoleInput();

    public AddressBookManager() {
        addAddrBookEntry("T", "J", "123");
        addAddrBookEntry("T", "G", "456");
        addAddrBookEntry("P", "B", "321");
        try {
            Path path = Paths.get("addressBook.csv");
            String csvFileContent = new String(Files.readAllBytes(path));
            List<AddressBookEntry> addressBookEntries = CsvImporter.importFromCSV(csvFileContent);
            for (AddressBookEntry entry : addressBookEntries) {
                addAddrBookEntry(entry.getFirstName(), entry.getLastName(), entry.getTelePhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (AddressBookEntry person : mainListOfAddressBookEntries) {
            System.out.println(person.getID() + " " + person.getFirstName() + " " + person.getLastName() + " " + person.getTelePhone());
        }
    }

    @Override
    public void addAddrBookEntry(String strFirst, String strLast, String strPhone) {
        System.out.println(strFirst + " " + strLast + " " + strPhone);
        AddressBookEntry addressBookEntry1 = new AddressBookEntry(mainListOfAddressBookEntries.size(), strFirst, strLast, strPhone);
        mainListOfAddressBookEntries.add(addressBookEntry1);
    }

    @Override
    public Iterator<AddressBookEntry> listAllAddrBookEntries() {
        return mainListOfAddressBookEntries.iterator();
    }

    @Override
    public List<AddressBookEntry> listAllSortedBookEntires() {
        Comparator addressBookComparator = new AddressBookComparator();
        mainListOfAddressBookEntries.sort(addressBookComparator);
        return mainListOfAddressBookEntries;
    }

    @Override
    public Iterator<AddressBookEntry> getAllAddrBookEntriesSrt(Comparator<AddressBookEntry> comparator) {
        List<AddressBookEntry> copy = new ArrayList<>(mainListOfAddressBookEntries);
        copy.sort(comparator);
        return copy.iterator();
    }

    @Override
    public List<AddressBookEntry> listAllSortedBookEntires(String textToSort) {
        Comparator addressBookComparator = null;
        try {
            if (textToSort.equalsIgnoreCase("1")) {
                addressBookComparator = ComparatorFactory.createComparator("id");
            } else if (textToSort.equalsIgnoreCase("2")) {
                addressBookComparator = ComparatorFactory.createComparator("firstname");
            } else if (textToSort.equalsIgnoreCase("3")) {
                addressBookComparator = ComparatorFactory.createComparator("lastname");
            } else if (textToSort.equalsIgnoreCase("4")) {
                addressBookComparator = ComparatorFactory.createComparator("telephone");
            }
//            else {
//            System.out.println("You chose wrong option we did sorting for you");
//            System.out.println("Going back to previous menu");
//            TimeUnit.SECONDS.sleep(5);
        } catch (WrongSortTypeException e) {
            System.out.println(e.getCause().getMessage());
        }
        mainListOfAddressBookEntries.sort(addressBookComparator);
        return mainListOfAddressBookEntries;
    }

    @Override
    public boolean removeAddrBookEntry(Integer id) {
        for (Iterator<AddressBookEntry> iterator = mainListOfAddressBookEntries.iterator(); iterator.hasNext(); ) {
            AddressBookEntry addressBookEntry = iterator.next();
            if (addressBookEntry.getID().equals(id)) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AddressBookEntry> findAddrBookEntry(String text) {
        List<AddressBookEntry> list = new ArrayList<>();
        Integer textToInt = null;
        try {
            textToInt = Integer.parseInt(text);
        } catch (Exception e) {
        }
        for (AddressBookEntry addressBookEntryText : mainListOfAddressBookEntries) {
            if (addressBookEntryText.getFirstName().contains(text) ||
                    addressBookEntryText.getLastName().contains(text) || addressBookEntryText.getTelePhone().contains(text) || addressBookEntryText.getID().equals(textToInt)) {
                list.add(addressBookEntryText);
            }
        }
        return list;
    }

    @Override
    public AddressBookEntry findAddrBookEntry(Integer id) {
        for (AddressBookEntry addressBookEntry : mainListOfAddressBookEntries) {
            if (addressBookEntry.getID().equals(id)) {
                return addressBookEntry;
            }
        }
        return null;
    }

    @Override
    public void editAddrBookEntry(Integer id, String phoneNumber) {
        AddressBookEntry addrBookEntryEdit = findAddrBookEntry(id);
        addrBookEntryEdit.setTelePhone(phoneNumber);
    }

//    @Override
//    public void sortAddrBookEntries(String text) {
//        mainListOfAddressBookEntries.sort(((o1, o2) -> o1.getTelePhone().compareToIgnoreCase(o2.getTelePhone())));
//    }
//
//    @Override
//    public void sortAddrBookEntries(Integer id) {
//
//    }

}
