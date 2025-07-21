package com.data.session10.controller;

import com.data.session10.entity.CreditCard;
import com.data.session10.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCard);
        return ResponseEntity.status(201).body(createdCreditCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateSpendingLimit(@PathVariable UUID id, @RequestParam Double newLimit) {
        CreditCard updatedCreditCard = creditCardService.updateSpendingLimit(id, newLimit);
        return ResponseEntity.ok(updatedCreditCard);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(@PathVariable UUID id, @RequestParam String newStatus) {
        CreditCard updatedCreditCard = creditCardService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updatedCreditCard);
    }
}