package com.apirest.webflux.controller;

import com.apirest.webflux.entity.Person;
import com.apirest.webflux.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("persons")
@Slf4j
public class PersonController {
    private final PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> listAll() {
        return personService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> findById (@PathVariable int id){
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> save (@Valid @RequestBody Person Person){
        return personService.save(Person);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update (@PathVariable int id , @Valid @RequestBody Person Person){
        return personService.update(Person.withId(id));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete (@PathVariable int id){
        return personService.delete(id);
    }
}
