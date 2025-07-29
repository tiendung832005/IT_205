package com.data.session16.controller;

import com.data.session16.model.entity.Feedback;
import com.data.session16.model.entity.User;
import com.data.session16.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Feedback> addFeedback(
            @RequestParam(required = false) Long playAreaId,
            @RequestParam(required = false) Long comboId,
            @RequestParam Integer rating,
            @RequestParam String comment,
            Principal principal) {
        User user = getUserFromPrincipal(principal); // Implement this method to fetch the user
        // Validate if the user has used the play area or combo before allowing feedback
        return ResponseEntity.ok(feedbackService.addFeedback(user, null, null, rating, comment));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @PutMapping("/{id}/reply")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Feedback> replyToFeedback(@PathVariable Long id, @RequestParam String reply) {
        return ResponseEntity.ok(feedbackService.replyToFeedback(id, reply));
    }

    private User getUserFromPrincipal(Principal principal) {
        // Implement logic to fetch User entity from the principal (e.g., using UserService)
        return null;
    }
}