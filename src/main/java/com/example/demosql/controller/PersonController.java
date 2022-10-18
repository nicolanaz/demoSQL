package com.example.demosql.controller;

import com.example.demosql.model.Person;
import com.example.demosql.model.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/people")
public class PersonController {
    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Person> getPeople() {
        return service.getPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable(name = "id") Long id) {
        return service.getPerson(id).blockOptional().isPresent()
                ? new ResponseEntity<>(service.getPerson(id).block(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Mono<Person> postPerson(@RequestBody Person person) {
        return service.postPerson(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPerson(@PathVariable Long id, @RequestBody Person person) {
        return service.putPerson(id, person).blockOptional().isPresent()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePerson(@PathVariable Long id) {
        return service.deletePerson(id);
    }
}
