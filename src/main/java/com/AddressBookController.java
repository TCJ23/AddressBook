package com;

import company.AddressBookEntry;
import company.AddressBookManager;
import company.IAddressBookManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressBookController {
    IAddressBookManager addressBookManager = new AddressBookManager();

    // ----------------------------------------------------------- OPTION 1 ----------------------------------------------------------------
    @RequestMapping(path = "find/{textToSearch}", method = RequestMethod.GET, produces = "application/json")
//    @GetMapping("/{textToSearch}")
    public String findOne(@PathVariable String textToSearch) {
        String result = ""; //        String textToSearch = consoleInput.getTextToSort();
        List<AddressBookEntry> adr = addressBookManager.findAddrBookEntry(textToSearch);
        if (adr.isEmpty() == false) {
            for (AddressBookEntry i : adr) {
                result = result + "Found following record " + i.toString() + "\n";
            }
            return result;
        } else if (adr.isEmpty()) {
            return ("Not sure what you looking for ?");
        }
        return null;
    }
// ----------------------------------------------------------- OPTION 2 ----------------------------------------------------------------

    @RequestMapping(path = "add/{strFirst}/{strLast}/{strPhone}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity addBookEntry(@PathVariable String strFirst, @PathVariable String strLast, @PathVariable String strPhone) {
        addressBookManager.addAddrBookEntry(strFirst, strLast, strPhone);
        return ResponseEntity.ok("We created following record: " + strFirst + strLast + strPhone);
    }

// ----------------------------------------------------------- OPTION 3 ----------------------------------------------------------------

    @RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
    public String removeAddrBookEntry(@PathVariable Integer id) {
        return "you deleted record: " + addressBookManager.removeAddrBookEntry(id);
    }


// ----------------------------------------------------------- OPTION 4 ----------------------------------------------------------------

    @RequestMapping(path = "listAll", method = RequestMethod.GET, produces = "application/json")
    public String listAll() {
        String result = "";
        for (AddressBookEntry x : addressBookManager.listAllAddrBookEntries()) {
            result = result + x.toString() + "\n";
        }
        return result;
    }
// ----------------------------------------------------------- OPTION 4 ----------------------------------------------------------------

    @RequestMapping(path = "updatePhone/{id}/{strPhone}", method = RequestMethod.PUT)
    public String updateAddrBookPhone (@PathVariable Integer id, @PathVariable String strPhone){
        addressBookManager.editAddrBookEntry(id, strPhone);
        return "we managed to change record" + addressBookManager.findAddrBookEntry(id).toString();
    }
//    @RequestMapping(path = "findMultiple/{s1}/{s2}", method = RequestMethod.GET, produces = "application/json")
//    public String addTwoStrings(@PathVariable String s1, @PathVariable String s2) {
//        return s1 + s2;
//    }
//    @RequestMapping(path = "findMultiple+Int/{s1}/{s2}/{i1}", method = RequestMethod.GET, produces = "application/json")
//    public String addTwoStringsWithInteger(@PathVariable String s1, @PathVariable String s2, @PathVariable Integer i1) {
//        return i1 + s1 + s2 ;
//    }
//    @GetMapping("hello")
//    @RequestMapping(path = "addressBook", method = RequestMethod.GET, produces = "application/json")
//    public String getHello() {
//        return "ADDRESS BOOK";
//    }
    //    @DeleteMapping("remove/{id}")
//    ResponseEntity delete(@PathVariable Integer id) {
//        addressBookManager.removeAddrBookEntry(id);
//        return ResponseEntity.ok("We removed record with ID :" + id);
}


