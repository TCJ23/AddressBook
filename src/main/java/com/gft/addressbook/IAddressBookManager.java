package com.gft.addressbook;

import com.gft.addressbook.model.AddressBookEntry;
import com.gft.addressbook.model.criteria.SearchCriteria;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAddressBookManager {

    public Iterator<AddressBookEntry> getAll();

    public Iterator<AddressBookEntry> get(Set<SearchCriteria> searchCriteriaList);

    public Long create(AddressBookEntry addressBookEntry);

    public void delete(AddressBookEntry addressBookEntry);

    public Optional<AddressBookEntry> getbyId(Long id);

    public void update(AddressBookEntry addressBookEntry);
}
