package com.data.session10.service;

import com.data.session10.entity.Account;
import com.data.session10.entity.Notification;
import com.data.session10.entity.Transaction;
import com.data.session10.repository.AccountRepository;
import com.data.session10.repository.TransactionRepository;
import com.data.session10.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(Transaction transaction) {
        Account sender = transaction.getSender();
        Account receiver = transaction.getReceiver();

        if (sender.getMoney() < transaction.getMoney()) {
            transaction.setStatus("thất bại");
            transaction.setCreatedAt(LocalDateTime.now());
            transactionRepository.save(transaction);
            System.err.println("Transaction failed: Insufficient funds.");
            return transaction;
        }

        sender.setMoney(sender.getMoney() - transaction.getMoney());
        receiver.setMoney(receiver.getMoney() + transaction.getMoney());
        accountRepository.save(sender);
        accountRepository.save(receiver);

        transaction.setStatus("thành công");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);

        Notification senderNotification = new Notification(
                null, sender, "Bạn đã chuyển " + transaction.getMoney() + " VND. Số dư hiện tại: " + sender.getMoney(),
                "chưa đọc", LocalDateTime.now());
        Notification receiverNotification = new Notification(
                null, receiver, "Bạn đã nhận " + transaction.getMoney() + " VND. Số dư hiện tại: " + receiver.getMoney(),
                "chưa đọc", LocalDateTime.now());

        notificationService.createNotification(senderNotification);
        notificationService.createNotification(receiverNotification);

        return transaction;
    }
}