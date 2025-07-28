package com.data.session15.controller;

import com.data.session15.model.entity.Review;
import com.data.session15.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Review addReview(@RequestParam Long userId, @RequestBody Review review) {
        return reviewService.addReview(userId, review);
    }

    @GetMapping("/products/{id}")
    public List<Review> getProductReviews(@PathVariable Long id) {
        return reviewService.getProductReviews(id);
    }
}