package com.example.frontendweb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.annotation.FacesConfig;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.logging.Logger;

@FacesConfig
@ApplicationScoped
@ApplicationPath("app")
public class AppContext extends Application {
    @Produces
    public Logger getLogger() {
        return Logger.getLogger("jakarta-demo");
    }
}
