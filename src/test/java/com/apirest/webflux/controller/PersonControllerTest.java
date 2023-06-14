package com.apirest.webflux.controller;

import com.apirest.webflux.entity.Person;
import com.apirest.webflux.service.PersonService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personServiceMock;

    private final Person person = PersonCreator.createValidPerson();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(personServiceMock.findAll()).thenReturn(Flux.just(person));
        BDDMockito.when(personServiceMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.just(person));
        BDDMockito.when(personServiceMock.save(PersonCreator.createPersonToBeSaved())).thenReturn(Mono.just(person));
        BDDMockito.when(personServiceMock.delete(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
        BDDMockito.when(personServiceMock.update(PersonCreator.createValidPerson())).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("find all returns a flux of person")
    public void findAllReturnFluxPersonWhenSuccess () {
        StepVerifier.create((personController.listAll()))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("find by id return Mono with person it exisits")
    public void findByIdReturnMonoPersonWhenSuccess() {
        StepVerifier.create((personController.findById(1)))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("save creates an person when successful")
    public void saveCreatesPersonWhenSuccessful() {
        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        StepVerifier.create(personController.save(personToBeSaved))
                .expectSubscription()
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    @DisplayName("delete removes the person when successful")
    public void deleteRemovePersonWhenSuccessful() {
        StepVerifier.create(personController.delete(1))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("update save updated person and returns empty mono when successful")
    public void updateSaveUpdatedPersonWhenSuccessful() {
        StepVerifier.create(personController.update(1, PersonCreator.createValidUpdatedPerson() ))
                .expectSubscription()
                .verifyComplete();
    }



}