package com.data.session14.service;

import com.data.session14.model.entity.RefreshToken;
import com.data.session14.model.entity.User;
import com.data.session14.repository.RefreshTokenRepository;
import com.data.session14.repository.UserRepository;
import com.data.session14.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public void register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    public Map<String, String> refreshAccessToken(String refreshToken, String addressIp) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!token.getAddressIp().equals(addressIp) || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired or IP mismatch");
        }

        String accessToken = jwtUtil.generateToken(token.getUser().getUsername(), token.getUser().getRole());
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        return tokens;
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        refreshTokenRepository.deleteByUser(user);
    }

    public RefreshToken createRefreshToken(User user, String addressIp) {
        List<RefreshToken> tokens = refreshTokenRepository.findByUserOrderByExpiryDateAsc(user);

        if (tokens.size() >= 2) {
            refreshTokenRepository.deleteById(tokens.get(0).getId());
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setAddressIp(addressIp);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));
        return refreshTokenRepository.save(refreshToken);
    }

    public void sendOtp(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String otp = generateOtp();
        user.setOtp(otp);
        userRepository.save(user);

        // Simulate sending OTP via email
        System.out.println("OTP sent to email: " + user.getEmail() + " | OTP: " + otp);
    }

    public Map<String, String> verifyOtp(String username, String password, String otp) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password) || !user.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid credentials or OTP");
        }

        user.setOtp(null);
        userRepository.save(user);

        String accessToken = jwtUtil.generateToken(user.getUsername(), "USER");
        RefreshToken refreshToken = createRefreshToken(user, "127.0.0.1"); // Replace with actual IP

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken.getToken());
        return tokens;
    }

    public void logoutAllDevices(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        refreshTokenRepository.deleteByUser(user);
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

}
