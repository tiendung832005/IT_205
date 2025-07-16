package com.data.session07.controller;

import com.data.session07.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public int countRemainingSeeds() {
        return statisticalService.countRemainingSeeds();
    }

    @GetMapping("/harvest-money")
    public double totalHarvestMoneyThisMonth() {
        return statisticalService.totalHarvestMoneyThisMonth();
    }

    @GetMapping("/payment-slips")
    public double totalPaymentSlipsThisMonth() {
        return statisticalService.totalPaymentSlipsThisMonth();
    }

    @GetMapping("/profit-loss")
    public Map<String, Double> profitLossOverYear() {
        return statisticalService.profitLossOverYear();
    }

    @GetMapping("/worker-salary")
    public double totalWorkerSalaryThisMonth() {
        return statisticalService.totalWorkerSalaryThisMonth();
    }
}
