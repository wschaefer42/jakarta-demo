package com.example.frontendweb.hello;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped @Getter
public class HelloData {
    private final List<String> registry = new ArrayList<>();

    public void append(String value) {
        registry.add(value);
    }
}
