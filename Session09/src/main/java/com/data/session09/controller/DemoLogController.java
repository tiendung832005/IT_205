package com.data.session09.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo-log")
public class DemoLogController {

    @GetMapping("/trace")
    public String traceLog() {
        log.trace("Đã ghi log trace");
        return "Trace log ghi thành công";
    }

    @GetMapping("/debug")
    public String debugLog() {
        log.debug("Đã ghi log debug");
        return "Debug log ghi thành công";
    }

    @GetMapping("/info")
    public String infoLog() {
        log.info("Đã ghi log info");
        return "Info log ghi thành công";
    }

    @GetMapping("/warning")
    public String warningLog() {
        log.warn("Đã ghi log warning");
        return "Warning log ghi thành công";
    }

    @GetMapping("/error")
    public String errorLog() {
        log.error("Đã ghi log error");
        return "Error log ghi thành công";
    }
}

