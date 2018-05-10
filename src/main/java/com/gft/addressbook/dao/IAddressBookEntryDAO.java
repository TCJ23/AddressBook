package com.gft.addressbook.dao;

import com.gft.addressbook.model.AddressBookEntry;
import org.springframework.data.repository.CrudRepository;

public interface IAddressBookEntryDAO extends CrudRepository <AddressBookEntry, Long> {

}
