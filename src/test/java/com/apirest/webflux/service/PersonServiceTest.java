package com.apirest.webflux.service;

import com.apirest.webflux.entity.Person;
import com.apirest.webflux.repository.PersonRepository;
import com.apirest.webflux.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepositoryMock;

    private final Person person = PersonCreator.createValidPerson();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(personRepositoryMock.findAll()).thenReturn(Flux.just(person));
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.just(person));
        BDDMockito.when(personRepositoryMock.save(PersonCreator.createPersonToBeSaved())).thenReturn(Mono.just(person));
        BDDMockito.when(personRepositoryMock.delete(ArgumentMatchers.any(Person.class))).thenReturn(Mono.empty());
        BDDMockito.when(personRepositoryMock.save(PersonCreator.createValidPerson())).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("find all returns a flux of person")
    public void findAllReturnFluxPersonWhenSuccess () {
        StepVerifier.create((personService.findAll()))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("find by id return Mono with person it exisits")
    public void findByIdReturnMonoPersonWhenSuccess() {
        StepVerifier.create((personService.findById(1)))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("find by id return Mono Error when person does not exists")
    public void findByIdReturnMonoPersonWhenEmptyMonoReturned() {
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
        StepVerifier.create((personService.findById(1)))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("save creates an person when successful")
    public void saveCreatesPersonWhenSuccessful() {
        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        StepVerifier.create(personService.save(personToBeSaved))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("delete removes the person when successful")
    public void deleteRemovePersonWhenSuccessful() {
        StepVerifier.create(personService.delete(1))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("delete returns Mono error when the person does not exists")
    public void deleteReturnMonoErrorWhenEmptyMonoReturned() {
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
        StepVerifier.create(personService.delete(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("update save updated person and returns empty mono when successful")
    public void updateSaveUpdatedPersonWhenSuccessful() {
        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        StepVerifier.create(personService.update(PersonCreator.createValidPerson()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("update return Mono error where person does not exists")
    public void updateReturnMonoErrorWhenEmptyMonoIsReturned() {
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        StepVerifier.create(personService.update(PersonCreator.createValidPerson()))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }
}