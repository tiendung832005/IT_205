package com.data.session10.service;

import com.data.session10.entity.Notification;
import com.data.session10.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByAccountId(UUID accountId) {
        return notificationRepository.findByAccountId(accountId);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}