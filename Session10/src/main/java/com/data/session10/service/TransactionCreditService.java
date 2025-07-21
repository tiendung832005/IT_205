package com.data.session10.service;

import com.data.session10.entity.Account;
import com.data.session10.entity.CreditCard;
import com.data.session10.entity.Notification;
import com.data.session10.entity.TransactionCredit;
import com.data.session10.repository.CreditCardRepository;
import com.data.session10.repository.TransactionCreditRepository;
import com.data.session10.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionCreditService {

    @Autowired
    private TransactionCreditRepository transactionCreditRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private NotificationService notificationService;

    public TransactionCredit createTransactionCredit(TransactionCredit transactionCredit) {
        CreditCard senderCard = transactionCredit.getCreditCardSender();
        Account receiverAccount = transactionCredit.getAccountReceiver();

        if (senderCard.getAmountSpent() + transactionCredit.getMoney() > senderCard.getSpendingLimit()) {
            transactionCredit.setStatus("thất bại");
            transactionCreditRepository.save(transactionCredit);
            System.err.println("Transaction failed: Exceeds spending limit.");
            return transactionCredit;
        }

        senderCard.setAmountSpent(senderCard.getAmountSpent() + transactionCredit.getMoney());
        creditCardRepository.save(senderCard);

        transactionCredit.setStatus("thành công");
        transactionCreditRepository.save(transactionCredit);

        Notification senderNotification = new Notification(
                null, senderCard.getAccount(),
                "Bạn đã chi tiêu " + transactionCredit.getMoney() + " VND qua thẻ tín dụng. Tổng chi tiêu: " + senderCard.getAmountSpent(),
                "chưa đọc", LocalDateTime.now());
        Notification receiverNotification = new Notification(
                null, receiverAccount,
                "Bạn đã nhận " + transactionCredit.getMoney() + " VND từ giao dịch thẻ tín dụng.",
                "chưa đọc", LocalDateTime.now());

        notificationService.createNotification(senderNotification);
        notificationService.createNotification(receiverNotification);

        return transactionCredit;
    }
}