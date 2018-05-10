package com.gft.addressbook.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.model.AddressBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@JsonFormat(shape=JsonFormat.Shape.ARRAY)

public class AddressBookController {
    //    @Autowired // pole -> setter -> konstruktor
    @Autowired
    private IAddressBookManager addressBookManager;

    @GetMapping("/addressBooks")
    public Iterator<AddressBookEntry> getall(){
        return addressBookManager.getAll();
    }

    @GetMapping("/addressBooks/{id}")
    public AddressBookEntry getbyId(@PathVariable Long id){
        Optional<AddressBookEntry> addressBookEntry = addressBookManager.getbyId(id);
        if (addressBookEntry.isPresent()){
            return addressBookEntry.get();
        }
        else {
            return null;// future exception handling
        }
    }

    @PostMapping("/addressBooks")
    public Long create(@RequestBody AddressBookEntry addressBookEntry){
        return addressBookManager.create(addressBookEntry);
    }

    @PutMapping("/addressBooks/{id}")
    public void put(@PathVariable Long id, @RequestBody AddressBookEntry userdata) {
        Optional<AddressBookEntry> bookEntry = addressBookManager.getbyId(id);
        if (bookEntry.isPresent()) {
            updateModel(userdata, bookEntry);
        }
        // TO DO
        else {} // future exception handling
    }

    private void updateModel(@RequestBody AddressBookEntry userdata, Optional<AddressBookEntry> bookEntry) {
        AddressBookEntry dbEntity = bookEntry.get();
        if(userdata.getFirstName() != null){
            dbEntity.setFirstName(userdata.getFirstName());
        }
        if(userdata.getLastName() != null) {
            dbEntity.setLastName(userdata.getLastName());
        }
        if (userdata.getTelePhone()!= null)
        {
            dbEntity.setTelePhone(userdata.getTelePhone());
        }
        addressBookManager.update(dbEntity);
    }

    @DeleteMapping("/addressBooks/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<AddressBookEntry> addressBookEntry = addressBookManager.getbyId(id);
        if (addressBookEntry.isPresent()){
            addressBookManager.delete(addressBookEntry.get());
        }
        else {} // future exception handling
    //     LAMBA      addressBookEntry.ifPresent(entry ->addressBookManager.delete(entry) );
    //METHOD REFERENC addressBookEntry.ifPresent(addressBookManager::delete);
    }
}


