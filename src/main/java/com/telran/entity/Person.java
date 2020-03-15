package com.telran.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("persons")
@TypeAlias("persons") //_class com.telran.entity.Person -> persons
public class Person {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;
}
