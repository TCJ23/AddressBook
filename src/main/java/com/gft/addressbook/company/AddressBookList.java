package com.gft.addressbook.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class AddressBookList<AddressBookEntry> extends ArrayList<AddressBookEntry> {

    //    DWA ROZWIĄZANIA HASHSET ALBO ITERACJA
//
    Set<Integer> uniqueIDs = new TreeSet<>();



    public boolean addAll(Collection<? extends AddressBookEntry> c) {
        System.out.println("OWN IMPLEMENTATION of LIST ADDALL !!!");
        return super.addAll(c);
    }

    public boolean add(AddressBookEntry e) {
        System.out.println("OWN IMPLEMENTATION of LIST ADD !!!");
            //    e
      //  uniqueIDs.add(((AddressBookEntry) e))
        return super.add(e);
    }

}