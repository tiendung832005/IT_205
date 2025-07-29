package com.data.session16.repository;

import com.data.session16.model.entity.Feedback;
import com.data.session16.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
}