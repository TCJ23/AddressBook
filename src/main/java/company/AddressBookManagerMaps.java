package company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookManagerMaps implements IAddressBookManager {
    private Integer counter = 0;
    ConsoleInput consoleInput = new ConsoleInput();
    Map<Integer, AddressBookEntry> idMap = new HashMap<Integer, AddressBookEntry>();
//    Map<String, AddressBookEntry> firstNameMap = new HashMap<String, AddressBookEntry>();
//    Map<String, AddressBookEntry> lastNameMap = new HashMap<String, AddressBookEntry>();
//    Map<String, AddressBookEntry> phoneMap = new HashMap<String, AddressBookEntry>();

    public AddressBookManagerMaps() {
        addAddrBookEntry("T", "J", "123interface");
        addAddrBookEntry("T", "G", "456interface");
        addAddrBookEntry("P", "B", "321");
    }

    @Override
    public void addAddrBookEntry(String strFirst, String strLast, String strPhone) {
        System.out.println(strFirst + " " + strLast + " " + strPhone);
        AddressBookEntry addressBookEntry1 = new AddressBookEntry(counter, strFirst, strLast, strPhone);
        idMap.put(addressBookEntry1.getID(), addressBookEntry1);
//        firstNameMap.put(addressBookEntry1.getFirstName(), addressBookEntry1);
//        lastNameMap.put(addressBookEntry1.getLastName(), addressBookEntry1);
//        phoneMap.put(addressBookEntry1.getTelePhone(), addressBookEntry1);
        counter++;
    }

    @Override
    public List<AddressBookEntry> listAllAddrBookEntries() {
        System.out.println("You chose option to list all");
        for (AddressBookEntry addressBookEntry : idMap.values()) {
            System.out.println(addressBookEntry.toString());
        }
        return null;
    }

    @Override
    public List<AddressBookEntry> listAllSortedBookEntires() {
        return null;
    }

    @Override
    public  List<AddressBookEntry> listAllSortedBookEntires(String textToSort) {
    return null;
    }

    @Override
    public List<AddressBookEntry> findAddrBookEntry(String text) {
        List<AddressBookEntry> list = new ArrayList<>();
        Integer textToInt = null;
        try {
            textToInt = Integer.parseInt(text);
        } catch (Exception e) {
        }
        AddressBookEntry val = idMap.get(textToInt);
        if (val != null){
            list.add(val);
        }
//        val = firstNameMap.get(text);
//        if (val != null){
//            list.add(val);
//        }
//        val = phoneMap.get(text);
//        if (val != null){
//            list.add(val);
//        }
//        val = lastNameMap.get(text);
//        if (val != null){
//            list.add(val);

        return list;
    }

    @Override
    public AddressBookEntry findAddrBookEntry(Integer id) {
        return idMap.get(id);
    }

//

    @Override
    public boolean removeAddrBookEntry(Integer id) {
        AddressBookEntry val = idMap.remove(id);
        if (val != null) {
            return true;
        }
        return false;
    }

    @Override
    public void editAddrBookEntry(Integer id, String phoneNumber) {

    }
}
