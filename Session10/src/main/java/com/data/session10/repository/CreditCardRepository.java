package com.data.session10.repository;

import com.data.session10.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    boolean existsByAccountId(UUID accountId);
}