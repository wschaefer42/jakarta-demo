package com.example.frontendweb.faces;

import com.example.frontendweb.Log;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Getter
public class AuthorBean implements Serializable {
    private final List<Author> authors;
    private String message;
    private Author current;

    @Setter private String firstName;
    @Setter private String lastName;

    public AuthorBean() {
        authors = new ArrayList<>(List.of(
                new Author(1, "Albert", "Einstein"),
                new Author(2, "Thomas", "Mann")));
    }

    public void saveAuthor() {
        if (!StringUtils.isBlank(firstName) && !StringUtils.isBlank(lastName)) {
            if (current == null) {
                var author = new Author(isMaxId() + 1, firstName, lastName);
                authors.add(author);
                firstName = lastName = "";
                message = "New author added";
            } else {
                current.setFirstName(firstName);
                current.setLastName(lastName);
                message = "Author updated";
            }
        } else {
            message = "First and last name are required";
        }
        Log.info(message);
    }

    public void displayAuthor(int id) {
        var author = authors.stream().filter(it -> it.getId() == id).findFirst();
        author.ifPresent(value -> {
                current = value;
                firstName = current.getFirstName();
                lastName = current.getLastName();
                Log.info("Selected %s", current);
        });
        PrimeFaces.current().ajax().update("inputForm:output");
    }

    public void reset() {
        current = null;
        firstName = null;
        lastName = null;
    }

    public boolean isSelected(int id) {
        return current != null && current.getId() == id;
    }

    private int isMaxId() {
        return authors.stream().mapToInt(Author::getId).max().orElse(0);
    }

}
