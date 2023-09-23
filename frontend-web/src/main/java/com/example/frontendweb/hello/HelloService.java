package com.example.frontendweb.hello;

import com.example.frontendweb.Log;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class HelloService {
    @Inject
    private HelloData helloData;

    public List<String> findAll() {
        return helloData.getRegistry();
    }

    public void add(String value) {
        if (helloData.getRegistry()
                .stream()
                .noneMatch(it -> it.equalsIgnoreCase(value))) {
            Log.info("Add %s to registry", value);
            helloData.append(value);
        }
    }
}
