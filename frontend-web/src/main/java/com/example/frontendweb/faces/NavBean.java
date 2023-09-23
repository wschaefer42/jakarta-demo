package com.example.frontendweb.faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Named
@RequestScoped
public class NavBean {
    @Setter
    private boolean authenticated = false;

    public String pageTwo() {
        return "PAGE_2";
    }

    public String pageOne() {
        return "PAGE_1";
    }

    public String nextPage() {
        return "page_three.xhtml";
    }

    public void login() {
        authenticated = true;
    }
}
