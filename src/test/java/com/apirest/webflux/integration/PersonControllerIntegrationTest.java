package com.apirest.webflux.integration;

import com.apirest.webflux.entity.Person;
import com.apirest.webflux.repository.PersonRepository;
import com.apirest.webflux.service.PersonService;
import com.apirest.webflux.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(PersonService.class)
public class PersonControllerIntegrationTest {

    @MockBean
    private PersonRepository personRepositoryMock;

    @Autowired
    private WebTestClient testClient;

    private final Person person = PersonCreator.createValidPerson();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(personRepositoryMock.findAll()).thenReturn(Flux.just(person));
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.just(person));
    }

    @Test
    @DisplayName("find all returns a flux of person")
    public void findAllReturnFluxPersonWhenSuccess () {
        testClient
                .get()
                .uri("/persons")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Person.class)
                .hasSize(1)
                .contains(person);
    }

    @Test
    @DisplayName("find by id return Mono with person it exisits")
    public void findByIdReturnMonoPersonWhenSuccess() {
        testClient
                .get()
                .uri("/persons/{id}",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .isEqualTo(person);
    }

    @Test
    @DisplayName("find by id return Mono Error when person does not exists")
    public void findByIdReturnMonoPersonWhenEmptyMonoReturned() {
        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
        testClient
                .get()
                .uri("/persons/{id}",5)
                .exchange()
                .expectStatus().isNotFound()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

}
