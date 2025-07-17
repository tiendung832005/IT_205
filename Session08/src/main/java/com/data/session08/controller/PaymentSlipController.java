package com.data.session08.controller;

import com.data.session08.entity.PaymentSlip;
import com.data.session08.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @PostMapping
    public ResponseEntity<PaymentSlip> addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        try {
            PaymentSlip savedPaymentSlip = paymentSlipService.addPaymentSlip(paymentSlip);
            return ResponseEntity.ok(savedPaymentSlip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentSlip> updatePaymentSlip(@PathVariable Long id, @RequestBody PaymentSlip paymentSlip) {
        try {
            PaymentSlip updatedPaymentSlip = paymentSlipService.updatePaymentSlip(id, paymentSlip);
            return ResponseEntity.ok(updatedPaymentSlip);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentSlip>> getAllPaymentSlips() {
        List<PaymentSlip> paymentSlips = paymentSlipService.getAllPaymentSlips();
        return ResponseEntity.ok(paymentSlips);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentSlip(@PathVariable Long id) {
        try {
            paymentSlipService.deletePaymentSlip(id);
            return ResponseEntity.ok("PaymentSlip deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}