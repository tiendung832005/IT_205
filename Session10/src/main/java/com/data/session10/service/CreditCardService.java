package com.data.session10.service;

import com.data.session10.entity.CreditCard;
import com.data.session10.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreditCard creditCard) {
        if (creditCardRepository.existsByAccountId(creditCard.getAccount().getId())) {
            throw new RuntimeException("Account already has a credit card");
        }
        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateSpendingLimit(UUID id, Double newLimit) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit card not found"));
        creditCard.setSpendingLimit(newLimit);
        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateStatus(UUID id, String newStatus) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit card not found"));
        creditCard.setStatus(newStatus);
        return creditCardRepository.save(creditCard);
    }
}