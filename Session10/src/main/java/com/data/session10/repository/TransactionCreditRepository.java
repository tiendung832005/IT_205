package com.data.session10.repository;

import com.data.session10.entity.CreditCard;
import com.data.session10.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, UUID> {
    List<TransactionCredit> findAllByCreditCardSenderAndCreatedAtBetween(CreditCard creditCardSender, LocalDateTime start, LocalDateTime end);
}