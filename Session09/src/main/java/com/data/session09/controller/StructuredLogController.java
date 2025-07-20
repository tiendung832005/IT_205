package com.data.session09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/structured-log")
public class StructuredLogController {

    @GetMapping("/event")
    public String logEvent() {
        log.info("Structured logging event triggered");
        return "Event logged in JSON format";
    }
}