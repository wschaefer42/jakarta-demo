package com.example.backendservice.bookstore;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/bookstore")
public class BookstoreResource {
    @Inject
    private BookstoreService bookstoreService;

    @GET
    public List<Book> list() {
        return bookstoreService.findAllBooks();
    }
}
