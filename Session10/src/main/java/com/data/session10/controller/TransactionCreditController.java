package com.data.session10.controller;

import com.data.session10.entity.TransactionCredit;
import com.data.session10.service.TransactionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-transactions")
public class TransactionCreditController {

    @Autowired
    private TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> createTransactionCredit(@RequestBody TransactionCredit transactionCredit) {
        TransactionCredit createdTransactionCredit = transactionCreditService.createTransactionCredit(transactionCredit);
        return ResponseEntity.status(201).body(createdTransactionCredit);
    }
}