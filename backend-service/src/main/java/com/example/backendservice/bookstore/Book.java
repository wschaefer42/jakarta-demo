package com.example.backendservice.bookstore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Year;
import java.util.Set;

@Entity
@Table(name="books")
@Getter @Setter @NoArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "year")
    private Short published = null;

    @ManyToOne
    private Category category;

    @ManyToMany
    private Set<Author> authors;

    public Book(String title, Year published, Set<Author> authors, Category category) {
        this.title = title;
        this.authors = authors;
        this.category = category;
        this.published = (short) published.getValue();
    }
}
