package com.telran.controller;

import com.telran.entity.Person;
import com.telran.entity.PersonSession;
import com.telran.repository.PersonRepository;
import com.telran.repository.PersonSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class EntryController {

    @Autowired
    private PersonSessionRepository personSessionRepository;

    @Autowired
    private PersonRepository personRepository;

    //1. registration
    //2. login

    @PostMapping("/register")
    public void register(@RequestBody Person person) {
        if (personRepository.existsByUsername(person.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        personRepository.save(person);
    }

    @PostMapping("/login")
    public Person login(@RequestBody Person person, HttpServletResponse response) {
        Person dbPerson = personRepository.findByUsernameAndPassword(person.getUsername(), person.getPassword());
        if (dbPerson == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        PersonSession personSession = PersonSession.builder()
                .person(dbPerson)
                .isValid(true)
                .build();

        personSessionRepository.save(personSession);
        response.setHeader("Authorization", personSession.getId());

        return dbPerson;
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal PersonSession personSession) {
        PersonSession dbPersonSession = personSessionRepository.findByIdAndIsValidTrue(personSession.getId());

        if (dbPersonSession != null) {
            dbPersonSession.setIsValid(false);
            personSessionRepository.save(dbPersonSession);
        }
    }
}
