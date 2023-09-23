package com.example.frontendweb.samples;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Named @RequestScoped @Getter
public class BeispielBean {
    @Setter private String name;
    private String output;
    private List<Name> card = new ArrayList<>();

    @Inject
    private BeispielService beispielService;

    public List<Name> getNames() {
        return beispielService.findAll();
    }

    public void addToCard(Name name) {
        card.add(name);
    }

    public void submit() {
        output = "Hello " +name;
    }
}
