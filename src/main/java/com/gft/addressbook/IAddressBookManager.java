package com.gft.addressbook;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public interface IAddressBookManager {

    void addAddrBookEntry(String strFirst, String strLast, String strPhone);

    Iterator<AddressBookEntry> listAllAddrBookEntries();

    Iterator<AddressBookEntry> getAllAddrBookEntriesSrt(Comparator<AddressBookEntry> comparator);

    List<AddressBookEntry> listAllSortedBookEntires();

    List<AddressBookEntry> listAllSortedBookEntires(String textToSort);

    List<AddressBookEntry> findAddrBookEntry(String text);

    AddressBookEntry findAddrBookEntry(Integer id);

    boolean removeAddrBookEntry(Integer id);

    void editAddrBookEntry(Integer id, String phoneNumber);
}
