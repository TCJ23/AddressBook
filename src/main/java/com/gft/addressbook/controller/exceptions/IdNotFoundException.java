package com.gft.addressbook.controller.exceptions;


public class IdNotFoundException extends Exception {
    public IdNotFoundException(String message) {
        super("Hey we didn't found ID you provided " + message);
    }
}
