package com.data.session07.controller;

import com.data.session07.entity.PaymentSlip;
import com.data.session07.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {
    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipService.getAllPaymentSlips();
    }

    @PostMapping
    public PaymentSlip addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        return paymentSlipService.addPaymentSlip(paymentSlip);
    }

    @GetMapping("/{id}")
    public PaymentSlip getPaymentSlipById(@PathVariable Long id) {
        return paymentSlipService.getPaymentSlipById(id);
    }
}