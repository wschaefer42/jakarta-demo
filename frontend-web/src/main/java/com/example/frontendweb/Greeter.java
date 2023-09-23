package com.example.frontendweb;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Greeter {
    @Setter private String message = "Hello";
    private List<String> languages = List.of("de", "en", "fr");

    public String getNow() {
        return LocalDate.now().toString();
    }
}
