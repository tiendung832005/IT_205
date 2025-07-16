package com.data.session07.service;

import com.data.session07.entity.PaymentSlip;
import com.data.session07.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSlipService {
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id).orElse(null);
    }

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }
}