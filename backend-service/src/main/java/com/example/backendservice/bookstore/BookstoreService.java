package com.example.backendservice.bookstore;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class BookstoreService {
    @PersistenceContext(unitName = "bookstore")
    private EntityManager em;

    public List<Book> findAllBooks() {
        return em.createQuery("select b from Book b order by b.title", Book.class).getResultList();
    }

    @PostConstruct
    void initDB() {
        var authors = new ArrayList<Author>();
        for(var author: List.of (
                new Author("Neal", "Ford"),
                new Author("Mark", "Richard")
        )) {
            em.persist(author);
            authors.add(author);
        }
        em.flush();
        Category architecture = new Category("Architecture");
        Book book1 = new Book(
                "Software Architecture: Hard Part",
                Year.of(2022),
                Set.of(authors.get(0), authors.get(1)),
                architecture);
        em.persist(book1);
        em.flush();
    }
}
