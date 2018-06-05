package com.gft.addressbook.core.manager;

import com.gft.addressbook.core.model.AddressBookEntry;
import com.gft.addressbook.core.model.criteria.AddressBookRequest;

import java.util.Iterator;
import java.util.Optional;

public interface IAddressBookManager {

    public Iterator<AddressBookEntry> getAll();

    public Iterator<AddressBookEntry> get(AddressBookRequest request);

    public Long create(AddressBookEntry addressBookEntry);

    public void delete(AddressBookEntry addressBookEntry);

    public Optional<AddressBookEntry> getbyId(Long id);

    public void update(AddressBookEntry addressBookEntry);
}
