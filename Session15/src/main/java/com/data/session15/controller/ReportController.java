package com.data.session15.controller;

import com.data.session15.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public BigDecimal getRevenue(@RequestParam String type) {
        return reportService.getRevenue(type);
    }
}