package com.telran.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("personSession")
@TypeAlias("personSession")
public class PersonSession {

    @Id
    private String id; //<-- sessionId

    @DBRef
    private Person person;

    private Boolean isValid;
}
