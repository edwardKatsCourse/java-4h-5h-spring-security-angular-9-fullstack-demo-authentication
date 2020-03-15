package com.telran.repository;

import com.telran.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    //Person findByUsername(String username);
    boolean existsByUsername(String username);

    Person findByUsernameAndPassword(String username, String password);
}
