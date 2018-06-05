package com.gft.addressbook.core.manager;

import com.gft.addressbook.core.dao.IAddressBookEntryDAO;
import com.gft.addressbook.core.model.AddressBookEntry;
import com.gft.addressbook.core.model.criteria.AddressBookEntrySpecification;
import com.gft.addressbook.core.model.criteria.AddressBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AddressBookManager implements IAddressBookManager {
    @Autowired
    private IAddressBookEntryDAO addressBookEntryDAO;

    @Override
    public Iterator<AddressBookEntry> get(AddressBookRequest request) {
//        if (request.getSearchCriteriaSet().isEmpty() || request.getSort() == null) {
////            return getAll();
////        } else {
//        IMPLEMENTED disjunction in AddressBookEntrySpecification
            AddressBookEntrySpecification specification = new AddressBookEntrySpecification(request.getSearchCriteriaSet());
            return addressBookEntryDAO.findAll(specification, request.getPageable()).getContent().iterator();
        }


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