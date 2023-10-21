package com.example.backendservice.bookstore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "authors")
@Getter @Setter @NoArgsConstructor
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, length = 100)
    private String firstname;
    @NotBlank @Column(nullable = false, length = 100)
    private String lastname;
    @Column(columnDefinition = "text")
    private String bio = null;

    @ManyToMany
    private Set<Book> books;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
