package com.data.session08.service;

import com.data.session08.entity.PaymentSlip;
import com.data.session08.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentSlipService {

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        paymentSlip.setCreatedAt(LocalDateTime.now());
        return paymentSlipRepository.save(paymentSlip);
    }

    public PaymentSlip updatePaymentSlip(Long id, PaymentSlip updatedPaymentSlip) {
        PaymentSlip paymentSlip = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("PaymentSlip not found with id: " + id));
        paymentSlip.setTitle(updatedPaymentSlip.getTitle());
        paymentSlip.setDescription(updatedPaymentSlip.getDescription());
        paymentSlip.setMoney(updatedPaymentSlip.getMoney());
        return paymentSlipRepository.save(paymentSlip);
    }

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    public void deletePaymentSlip(Long id) {
        if (!paymentSlipRepository.existsById(id)) {
            throw new NoSuchElementException("PaymentSlip not found with id: " + id);
        }
        paymentSlipRepository.deleteById(id);
    }
}