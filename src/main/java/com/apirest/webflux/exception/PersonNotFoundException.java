package com.apirest.webflux.exception;

public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
        super("Person not found");
    }

}
