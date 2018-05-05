package com.gft.addressbook.comparators;

public class WrongSortTypeException extends Exception {

    public WrongSortTypeException() {
    }

    public WrongSortTypeException(String message, Throwable cause) {
        super("You can only sort by id, firstname,lastname or telephone" + message, cause);
    }

}