package com.apirest.webflux.service;

import com.apirest.webflux.entity.Person;
import com.apirest.webflux.exception.PersonNotFoundException;
import com.apirest.webflux.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Person> findById(int id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new PersonNotFoundException()))
                .log();
    }

    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }

    public Mono<Void> update(Person person){
        return findById(person.getId())
                .flatMap(validAnime -> personRepository.save(person))
                .then();
    }

    public Mono<Void> delete(int id) {
        return findById(id)
                .flatMap(personRepository::delete);
    }
}
