package com.gft.addressbook;

import com.gft.addressbook.company.AddressBookManager;

public class AddressBookManagerTest {


    @org.junit.Test
    public void dummyBehaviour()  {
        AddressBookManager addressBookManager = new AddressBookManager();
        addressBookManager.addAddrBookEntry("T", "J","123");
        addressBookManager.addAddrBookEntry("P", "B","321");
        addressBookManager.listAllAddrBookEntries();
        addressBookManager.findAddrBookEntry(1);
    }

    @org.junit.Test
    public void listAllAddrBookEntries() {
    }

    @org.junit.Test
    public void findAddrBookEntry() {
    }
}