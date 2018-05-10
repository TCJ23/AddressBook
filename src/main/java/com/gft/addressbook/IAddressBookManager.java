package com.gft.addressbook;

import com.gft.addressbook.model.AddressBookEntry;

import java.util.Iterator;
import java.util.Optional;

public interface IAddressBookManager {

    public Iterator<AddressBookEntry> getAll();

    public Long create(AddressBookEntry addressBookEntry);

    public void delete(AddressBookEntry addressBookEntry);

    public Optional<AddressBookEntry> getbyId(Long id);

    public void update(AddressBookEntry addressBookEntry);
}
