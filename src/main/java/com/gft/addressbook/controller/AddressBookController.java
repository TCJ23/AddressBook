package com.gft.addressbook.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.exceptions.IdNotFoundException;
import com.gft.addressbook.model.AddressBookEntry;
import com.gft.addressbook.model.criteria.SearchCriteria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@JsonFormat(shape = JsonFormat.Shape.ARRAY)

public class AddressBookController {
    //    @Autowired // pole -> setter -> konstruktor
    @Autowired
    private IAddressBookManager addressBookManager;

    @GetMapping("/addressBooks")
    public Iterator<AddressBookEntry> getall(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            HashSet<SearchCriteria> hashSet = new HashSet<>();
            hashSet.add(new SearchCriteria(search));
            return addressBookManager.get(hashSet);
        } else {
            return addressBookManager.getAll();
        }
    }

    @GetMapping("/addressBooks/{id}")
    public AddressBookEntry getbyId(@PathVariable Long id) throws IdNotFoundException {
        Optional<AddressBookEntry> addressBookEntry = addressBookManager.getbyId(id);
        if (addressBookEntry.isPresent()) {
            return addressBookEntry.get();
        } else {
            throw new IdNotFoundException(id.toString());
        }
    }

    @PostMapping("/addressBooks")
    public List<Long> create(@RequestBody List<AddressBookEntry> addressBookEntries) {
/*  REGULAR FOR EACH
        List<Long> IDs = new ArrayList<>();
        for (AddressBookEntry bookEntry : addressBookEntries) {
            IDs.add(addressBookManager.create(bookEntry));
        }
*/
/*  LAMBDA + FOR EACH
        addressBookEntries.forEach(bookEntry -> IDs.add(addressBookManager.create(bookEntry)));
        return IDs;
*/
        /* STREAM */
        return addressBookEntries.stream()
                .map(bookEntry -> addressBookManager.create(bookEntry))
                .collect(Collectors.toList());
    }

    @PutMapping("/addressBooks/{id}")
    public void put(@PathVariable Long id, @RequestBody AddressBookEntry userdata) throws IdNotFoundException {
        Optional<AddressBookEntry> bookEntry = addressBookManager.getbyId(id);
        if (bookEntry.isPresent()) {
            updateModel(userdata, bookEntry);
        }
        // TO DO
        else {
            throw new IdNotFoundException(id.toString());
        }
    }

    private void updateModel(@RequestBody AddressBookEntry userdata, Optional<AddressBookEntry> bookEntry) {
        AddressBookEntry dbEntity = bookEntry.get();
        if (userdata.getFirstName() != null) {
            dbEntity.setFirstName(userdata.getFirstName());
        }
        if (userdata.getLastName() != null) {
            dbEntity.setLastName(userdata.getLastName());
        }
        if (userdata.getTelePhone() != null) {
            dbEntity.setTelePhone(userdata.getTelePhone());
        }
        addressBookManager.update(dbEntity);
    }

    @DeleteMapping("/addressBooks/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IdNotFoundException {
        Optional<AddressBookEntry> addressBookEntry = addressBookManager.getbyId(id);
        if (addressBookEntry.isPresent()) {
            addressBookManager.delete(addressBookEntry.get());
        } else {
            throw new IdNotFoundException(id.toString());
        }
        //     LAMBA      addressBookEntry.ifPresent(entry ->addressBookManager.delete(entry) );
        //METHOD REFERENC addressBookEntry.ifPresent(addressBookManager::delete);
    }

    @ExceptionHandler({IdNotFoundException.class})
    private ResponseEntity handler(IdNotFoundException e) {
//        HashMap<String, Object> handlerMap = new HashMap<>();
//        handlerMap.put("code", HttpStatus.NOT_FOUND.value());
//        handlerMap.put("message", e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(handlerMap);
        HandlerResponse handlerResponse = new HandlerResponse();
        handlerResponse.setCode(HttpStatus.NOT_FOUND.value());
        handlerResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handlerResponse);
    }

    @Getter
    @Setter
    private class HandlerResponse {
        private int code;
        private String message;
    }
}


