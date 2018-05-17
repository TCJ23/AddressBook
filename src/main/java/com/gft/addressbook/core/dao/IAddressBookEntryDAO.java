package com.gft.addressbook.core.dao;

import com.gft.addressbook.core.model.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAddressBookEntryDAO extends JpaRepository<AddressBookEntry, Long>, JpaSpecificationExecutor<AddressBookEntry> {

}
