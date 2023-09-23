package com.example.frontendweb.samples;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Name {
    @Setter private String firstName;
    @Setter private String lastName = "";
    public Name(String firstName) {
        this.firstName = firstName;
    }
}
