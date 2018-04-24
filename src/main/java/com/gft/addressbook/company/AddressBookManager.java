package com.gft.addressbook.company;

import com.gft.addressbook.ConsoleInput;
import com.gft.addressbook.CsvImporter;
import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.comparators.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class AddressBookManager implements IAddressBookManager {
    private List<AddressBookEntry> mainListOfAddressBookEntries = new ArrayList<>(); ///ID ISSUE
//    ------------------------------------------------------------------------DAO IMPLEMENTATION -------------------------------------------------------------------------------------------------------------
//    AddressBookEntryDAO dao = new AddressBookEntryDAO();
//    private List<AddressBookEntry> mainListOfAddressBookEntries = new AddressBookList<>();
//    private Set<Integer> uniqueIds = new TreeSet<>();
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

//            for (AddressBookEntry addrBookValidation : addressBookEntries) {
//                if (uniqueIds.add(addrBookValidation.getID()) == false){
//                    throw new IllegalArgumentException("Duplicated ID" + addrBookValidation.getID());
//                }
//            }
//            mainListOfAddressBookEntries.addAll(addressBookEntries); // ID ISSUE
            for (AddressBookEntry addressBookEntryFromCSV : addressBookEntries) {
                addAddrBookEntry(addressBookEntryFromCSV.getFirstName(),addressBookEntryFromCSV.getLastName(),addressBookEntryFromCSV.getTelePhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (AddressBookEntry person : mainListOfAddressBookEntries) {
            System.out.println(person.getID() + " " + person.getFirstName() + " " + person.getLastName() + " " + person.getTelePhone());
        }
    }
///   -------------------------------------------------------------------------GENERATE DATA -------------------------------------------------------------------------------------------------------------

    @Override
    public void addAddrBookEntry(String strFirst, String strLast, String strPhone) {
        System.out.println(strFirst + " " + strLast + " " + strPhone);
        AddressBookEntry addressBookEntry1 = new AddressBookEntry(mainListOfAddressBookEntries.size(), strFirst, strLast, strPhone);
//            if (uniqueIds.add(addressBookEntry1.getID()) == false){
//                throw new IllegalArgumentException("Duplicated ID " + addressBookEntry1.getID());
//            }
        mainListOfAddressBookEntries.add(addressBookEntry1);
    }

    @Override
    public List<AddressBookEntry> listAllAddrBookEntries() {
//        System.out.println("You chose option to list all");
//        for (AddressBookEntry addressBookEntry : mainListOfAddressBookEntries) {
//            System.out.println(addressBookEntry.toString());
//        }
        return mainListOfAddressBookEntries;
    }

    @Override
    public List<AddressBookEntry> listAllSortedBookEntires() {
//        List<AddressBookEntry> sortedList = new ArrayList<>(mainListOfAddressBookEntries);
        Comparator addressBookComparator = new AddressBookComparator();
        mainListOfAddressBookEntries.sort(addressBookComparator);
        return mainListOfAddressBookEntries;
    }

    @Override
    public List<AddressBookEntry> listAllSortedBookEntires(String textToSort) throws InterruptedException {
        Comparator addressBookComparator = new AddressBookComparator();
        if (textToSort.equalsIgnoreCase("1")) {
            addressBookComparator = new AddressBookComparatorById();
        } else if (textToSort.equalsIgnoreCase("2")) {
            addressBookComparator = new AddressBookComparatorByFirstName();
        } else if (textToSort.equalsIgnoreCase("3")) {
            addressBookComparator = new AddressBookComparatorByLastName();
        } else if (textToSort.equalsIgnoreCase("4")) {
            addressBookComparator = new AddressBookComparatorByTelephone();
        }
        else {
            System.out.println("You chose wrong option we did sorting for you");
            System.out.println("Going back to previous menu");
            TimeUnit.SECONDS.sleep(5);
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
//                System.out.println(addressBookEntry.toString());
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
