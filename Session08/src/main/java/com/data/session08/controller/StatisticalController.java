package com.data.session08.controller;

import com.data.session08.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/top-dishes")
    public ResponseEntity<List<Map<String, Object>>> getTopDishes() {
        try {
            List<Map<String, Object>> topDishes = statisticalService.getTopDishes();
            return ResponseEntity.ok(topDishes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/top-customers")
    public ResponseEntity<List<Map<String, Object>>> getTopCustomers() {
        try {
            List<Map<String, Object>> topCustomers = statisticalService.getTopCustomers();
            return ResponseEntity.ok(topCustomers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<Double> getCurrentMonthExpenses() {
        try {
            Double expenses = statisticalService.getCurrentMonthExpenses();
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<Map<String, Double>> getMonthlyExpenses() {
        try {
            Map<String, Double> monthlyExpenses = statisticalService.getMonthlyExpenses();
            return ResponseEntity.ok(monthlyExpenses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<Map<String, Double>> getMonthlyRevenue() {
        try {
            Map<String, Double> monthlyRevenue = statisticalService.getMonthlyRevenue();
            return ResponseEntity.ok(monthlyRevenue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/top-employee")
    public ResponseEntity<Map<String, Object>> getTopEmployee() {
        try {
            Map<String, Object> topEmployee = statisticalService.getTopEmployee();
            return ResponseEntity.ok(topEmployee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}