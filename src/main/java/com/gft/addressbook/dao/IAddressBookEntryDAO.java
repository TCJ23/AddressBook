package com.gft.addressbook.dao;

import com.gft.addressbook.model.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAddressBookEntryDAO extends JpaRepository<AddressBookEntry, Long>, JpaSpecificationExecutor<AddressBookEntry> {

}
