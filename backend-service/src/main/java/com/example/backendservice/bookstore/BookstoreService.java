package com.example.backendservice.bookstore;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
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

    public Author insertAuthor(Author author) {
        if (author.getId() != null) {
            throw new RuntimeException("ID must be null");
        }
        em.persist(author);
        em.flush();
        return author;
    }

    public void updateAuthor(Author author) {
        if (author.getId() == null) {
            throw new RuntimeException("ID cannot be null");
        }
        em.merge(author);
        em.flush();
    }

    @Inject
    UserTransaction ut;

    // @Transactional
    @PostConstruct
    void initDB() {
        try {
            ut.begin();
            var authors = new ArrayList<Author>();
            for (var author : List.of(
                    new Author("Neal", "Ford"),
                    new Author("Mark", "Richard")
            )) {
                em.persist(author);
                authors.add(author);
            }
            // em.flush();
            Category architecture = new Category("Architecture");
            em.persist(architecture);
            Book book1 = new Book(
                    "Software Architecture: Hard Part",
                    Year.of(2022),
                    Set.of(authors.get(0), authors.get(1)),
                    architecture);
            em.persist(book1);
            em.flush();
            ut.commit();
        } catch (Exception ex) {
            try {
                ut.rollback();
            } catch (Exception ignored) {}
            throw new RuntimeException(ex);
        }
    }
}
