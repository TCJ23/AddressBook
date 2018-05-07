package com.gft.addressbook.comparators;

public class WrongSortTypeException extends Exception {
    public WrongSortTypeException(String type) {
        super("Invalid sort type: " + type);
    }
}