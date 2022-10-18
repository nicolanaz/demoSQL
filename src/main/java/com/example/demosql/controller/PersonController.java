package com.example.demosql.controller;

import com.example.demosql.model.Person;
import com.example.demosql.model.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
@RequestMapping("/people")
public class PersonController {
    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public Flux<Person> getPeople() {
        return service.getPeople();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getPerson(@PathVariable(name = "id") Long id) {
        return service.getPerson(id).blockOptional().isPresent()
                ? new ResponseEntity<>(service.getPerson(id).block(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseBody
    public Mono<Person> postPerson(@RequestBody Person person) {
        return service.postPerson(person);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> putPerson(@PathVariable Long id, @RequestBody Person person) {
        return service.putPerson(id, person).blockOptional().isPresent()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    @ResponseBody
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Mono<Void> deletePerson(@PathVariable Long id) {
        return service.deletePerson(id);
    }

    @MessageMapping("peopleStream")
    public Flux<Person> getPeopleStream() {
        return service.getPeople();
    }
}
