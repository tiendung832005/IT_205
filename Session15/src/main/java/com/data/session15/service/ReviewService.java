package com.data.session15.service;

import com.data.session15.model.entity.Review;
import com.data.session15.model.entity.User;
import com.data.session15.repository.OrderRepository;
import com.data.session15.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public Review addReview(Long userId, Review review) {
        boolean hasPurchased = orderRepository.findByUserId(userId).stream()
                .flatMap(order -> order.getOrderItems().stream())
                .anyMatch(item -> item.getProduct().getId().equals(review.getProduct().getId()));

        if (!hasPurchased) {
            throw new RuntimeException("User has not purchased this product");
        }

        review.setCreatedDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getProductReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}