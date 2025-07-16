package com.data.session07.repository;

import com.data.session07.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}