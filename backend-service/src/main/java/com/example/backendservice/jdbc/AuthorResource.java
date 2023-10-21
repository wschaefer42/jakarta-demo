package com.example.backendservice.jdbc;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.sql.SQLException;
import java.util.List;

@Path("/authors-jdbc")
public class AuthorResource {
    @Inject
    private AuthorService authorService;

    @GET
    public List<AuthorData> list() throws SQLException {
        return authorService.findAll();
    }
}
