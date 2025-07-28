package com.data.session14.repository;

import com.data.session14.model.entity.RefreshToken;
import com.data.session14.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUserOrderByExpiryDateAsc(User user);
    void deleteByUser(User user);
}