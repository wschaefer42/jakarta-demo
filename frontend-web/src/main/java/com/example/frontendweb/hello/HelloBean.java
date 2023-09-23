package com.example.frontendweb.hello;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@Named @RequestScoped @Getter
public class HelloBean {
    @Setter private String input;
    private String output;

    @Inject @Getter(AccessLevel.PRIVATE)
    private HelloService helloService;

    public List<String> getRegistered() {
        return helloService.findAll();
    }

    public void submit() {
        if (StringUtils.isNotBlank(input)) {
            output = "Hello " + input;
            helloService.add(input);
        }
    }
}
