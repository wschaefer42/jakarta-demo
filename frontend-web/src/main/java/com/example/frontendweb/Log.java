package com.example.frontendweb;

import jakarta.inject.Singleton;
import java.util.logging.Logger;

@Singleton
public class Log {
    private static final Logger logger = Logger.getLogger("jakarta-demo");

    public static void info(String msg, Object...args) {
        logger.info(String.format(msg, args));
    }
}
