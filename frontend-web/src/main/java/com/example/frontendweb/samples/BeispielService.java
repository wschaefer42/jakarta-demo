package com.example.frontendweb.samples;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BeispielService {
    public List<Name> findAll() {
        return List.of(
                new Name("Peter"),
                new Name("Hans"),
                new Name("Susanne"));
    }
}
