package com.gft.addressbook.controller;

import com.gft.addressbook.IAddressBookManager;
import com.gft.addressbook.company.AddressBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressBookController {
    //    @Autowired // pole -> setter -> konstruktor
    private IAddressBookManager addressBookManager;

    @Autowired
    public AddressBookController(IAddressBookManager addressBookManager) {
        this.addressBookManager = addressBookManager;
    }

    /* find single record by ID */
    @GetMapping("/address/{id}")
    public AddressBookEntry singleByID(@PathVariable(value = "id") Integer id){
        return addressBookManager.findAddrBookEntry(id);
    }
/*DEPRECATED
    @GetMapping("/find/{textToSearch}")
    public List<AddressBookEntry> findOne(@PathVariable String textToSearch) {
        List<AddressBookEntry> adr = addressBookManager.findAddrBookEntry(textToSearch);
        return adr;
    }
    */

    /* Opt to list all addresses */
    @GetMapping("/addresses")
    public List<AddressBookEntry> allAdrBook(){
        return addressBookManager.listAllAddrBookEntries();
    }

    /* opt to sort by variable*/
    @GetMapping("/addresses/sort")
    public List<AddressBookEntry> listAll(@RequestParam(value="sort", required = false, defaultValue="defaultSort") String textToSort) throws InterruptedException {
        return addressBookManager.listAllSortedBookEntires(textToSort);
    }

    /** OPTION 6 */

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

    @RequestMapping(path = "updatePhone/{id}/{strPhone}", method = RequestMethod.PUT)
    public String updateAddrBookPhone(@PathVariable Integer id, @PathVariable String strPhone) {
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


