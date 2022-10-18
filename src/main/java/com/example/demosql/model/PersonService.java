package com.example.demosql.model;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PersonService {
    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Flux<Person> getPeople() {
        return repository.findAll();
    }

    public Mono<Person> getPerson(Long id) {
        return repository.findById(id);
    }

    public Mono<Person> postPerson(Person person) {
        return repository.save(person);
    }

    public Mono<Person> putPerson(Long id, Person person) {
        if (isExist(id)) {
            repository.deleteById(id);
            person.setId(id);
            return repository.save(person);
        } else {
            return Mono.empty();
        }
    }

    public Mono<Void> deletePerson(Long id) {
        return repository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    private boolean isExist(Long id) {
        return repository.existsById(id).block();
    }
}
