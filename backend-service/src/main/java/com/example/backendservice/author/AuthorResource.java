package com.example.backendservice.author;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;

@Path("/authors")
public class AuthorResource {
    @Inject
    private AuthorService authorService;

    @GET
    public List<Author> list() {
        return authorService.findAll();
    }

    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") Long id) {
        return authorService.findById(id);
    }

}
