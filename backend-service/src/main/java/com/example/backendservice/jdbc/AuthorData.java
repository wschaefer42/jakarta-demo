package com.example.backendservice.jdbc;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorData {
    private Long id;
    private String firstname;
    private String lastname;
    private String bio;

    public AuthorData(Long id, String firstname, String lastname, String bio) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
    }
}
