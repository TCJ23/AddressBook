package com.gft.addressbook.model;

import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.dao.IAddressBookEntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AddressBookManager implements IAddressBookManager {
    @Autowired
    private IAddressBookEntryDAO addressBookEntryDAO;

    @Override
    public Iterator<AddressBookEntry> getAll() {
        return addressBookEntryDAO.findAll().iterator();
    }

    @Override
    public Long create(AddressBookEntry addressBookEntry) {
        AddressBookEntry saveEntity = addressBookEntryDAO.save(addressBookEntry);
        return saveEntity.getID();
    }

    @Override
    public void delete(AddressBookEntry addressBookEntry) {
        addressBookEntryDAO.delete(addressBookEntry);
    }

    public Optional<AddressBookEntry> getbyId(Long id) {
    return addressBookEntryDAO.findById(id);
    }

    @Override
    public void update(AddressBookEntry addressBookEntry) {
        addressBookEntryDAO.save(addressBookEntry);
    }
}