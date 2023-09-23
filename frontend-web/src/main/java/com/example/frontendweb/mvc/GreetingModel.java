package com.example.frontendweb.mvc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named("greeting")
@RequestScoped
@Getter @Setter
public class GreetingModel {
    private String message;
}
