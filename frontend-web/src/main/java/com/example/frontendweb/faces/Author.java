package com.example.frontendweb.faces;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Author {
    private int id;
    private String firstName;
    private String lastName;

    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("[Author] %s %s", firstName, lastName);
    }
}
