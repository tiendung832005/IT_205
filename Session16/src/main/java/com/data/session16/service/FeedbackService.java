package com.data.session16.service;

import com.data.session16.model.entity.Combo;
import com.data.session16.model.entity.Feedback;
import com.data.session16.model.entity.PlayArea;
import com.data.session16.model.entity.User;
import com.data.session16.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Feedback addFeedback(User user, PlayArea playArea, Combo combo, Integer rating, String comment) {
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setPlayArea(playArea);
        feedback.setCombo(combo);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback replyToFeedback(Long id, String reply) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setReply(reply);
        feedback.setRepliedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }
}