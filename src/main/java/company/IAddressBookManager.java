package company;

import java.util.List;

public interface IAddressBookManager {

    void addAddrBookEntry(String strFirst, String strLast, String strPhone);

    List<AddressBookEntry> listAllAddrBookEntries();

    List<AddressBookEntry> listAllSortedBookEntires();

    List<AddressBookEntry> listAllSortedBookEntires(String textToSort) throws InterruptedException;

    List<AddressBookEntry> findAddrBookEntry(String text);

    AddressBookEntry findAddrBookEntry(Integer id);

//    void sortAddrBookEntries(String text);

//    void sortAddrBookEntries(Integer id);

    boolean removeAddrBookEntry(Integer id);

    void editAddrBookEntry(Integer id, String phoneNumber);
}
