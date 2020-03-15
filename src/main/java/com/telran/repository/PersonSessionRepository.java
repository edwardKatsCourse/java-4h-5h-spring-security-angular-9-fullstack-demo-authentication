package com.telran.repository;

import com.telran.entity.PersonSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonSessionRepository extends MongoRepository<PersonSession, String> {

    PersonSession findByIdAndIsValidTrue(String id);
}
