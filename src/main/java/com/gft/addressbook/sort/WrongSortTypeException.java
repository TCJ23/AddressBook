package com.gft.addressbook.sort;

public class WrongSortTypeException extends Exception {
    public WrongSortTypeException(String type) {
        super("Invalid sort type: " + type);
    }
}