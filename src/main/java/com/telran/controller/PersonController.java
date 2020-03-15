package com.telran.controller;

import com.telran.entity.Person;
import com.telran.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/persons")
    public List<Person> securedGetAll() {
        return personRepository.findAll();
    }
}
