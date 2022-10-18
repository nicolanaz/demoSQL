package com.example.demosql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    private Long id = new Random().nextLong(1, 1000000);
    private String name;
    private int age;
    private String profession;
    private boolean married;
}
