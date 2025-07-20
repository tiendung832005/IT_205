package com.data.session09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class SimpleApiController {

    @GetMapping("/error-demo")
    public String errorDemo() {
        log.info("Endpoint /error-demo accessed");
        if (true) { // Simulate an error
            throw new RuntimeException("Simulated exception occurred");
        }
        return "This will never be reached";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex) {
        log.error("An error occurred: {}", ex.getMessage(), ex);
        return "An error occurred: " + ex.getMessage();
    }
}