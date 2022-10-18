package com.example.demosql;

import com.example.demosql.model.Person;
import com.example.demosql.model.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Supplier;

@Configuration
@AllArgsConstructor
public class PersonReporter {
    private PersonService service;

    @Bean
    public Supplier<List<Person>> reportPeople() {
        return () -> service.getPeople().collectList().block();
    }
}
