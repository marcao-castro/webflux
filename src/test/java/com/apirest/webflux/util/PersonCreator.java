package com.apirest.webflux.util;

import com.apirest.webflux.entity.Person;

public class PersonCreator {

    public static Person createPersonToBeSaved(){
        return Person.builder()
                .name("JOSE")
                .build();
    }

    public static Person createValidPerson(){
        return Person.builder()
                .id(1)
                .name("JOSE")
                .build();
    }

    public static Person createValidUpdatedPerson(){
        return Person.builder()
                .id(1)
                .name("JOSE 2")
                .build();
    }

}
