package com.apirest.webflux.repository;

import com.apirest.webflux.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository <Person, Integer>{

}
