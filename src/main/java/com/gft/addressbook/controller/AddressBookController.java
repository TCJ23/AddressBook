package com.gft.addressbook.controller;

import com.gft.addressbook.controller.exceptions.IdNotFoundException;
import com.gft.addressbook.core.manager.IAddressBookManager;
import com.gft.addressbook.core.model.AddressBookEntry;
import com.gft.addressbook.core.model.criteria.SearchCriteria;
import com.gft.addressbook.core.model.criteria.SearchCriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class AddressBookController {
    //    @Autowired // pole -> setter -> konstruktor
    @Autowired
    private IAddressBookManager addressBookManager;

    @GetMapping("/addressBooks")
    public Iterator<AddressBookEntry> getall(@RequestParam(value = "search", required = false) String search,
                                             @RequestParam(value = "sort", required = false) String sort) {
        SearchCriteriaBuilder builder = SearchCriteria.builder();
        if (StringUtils.isNotBlank(search)) {
            for (String s : StringUtils.split(search, ",")) {
                builder.searchBy(s);
            }
        }
        if (StringUtils.isNotBlank(sort)){
            String[] sorting = StringUtils.split(sort, ":");
            builder.sortBy(sorting[0], SearchCriteriaBuilder.SortOrder.fromString(sorting[1]));
        }
        return addressBookManager.get(builder.buildResult());
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


