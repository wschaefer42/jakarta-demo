package com.example.frontendweb.mvc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
@RequestScoped
@Controller
public class HelloController {
    @Inject
    private GreetingModel greeting;

    @GET
    public String hello() {
        greeting.setMessage("Hello from Jakarta MVC");
        return "greeting.jsp";
    }
}
