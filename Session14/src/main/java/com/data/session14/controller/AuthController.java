package com.data.session14.controller;

import com.data.session14.model.entity.User;
import com.data.session14.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String token = authService.login(credentials.get("username"), credentials.get("password"));
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestParam String refreshToken,
                                                            @RequestHeader("X-Forwarded-For") String addressIp) {
        Map<String, String> tokens = authService.refreshAccessToken(refreshToken, addressIp);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication) {
        String username = authentication.getName();
        authService.logout(username);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody Map<String, String> credentials) {
        Map<String, String> tokens = authService.verifyOtp(credentials.get("username"), credentials.get("password"), credentials.get("otp"));
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout-all")
    public ResponseEntity<String> logoutAll(Authentication authentication) {
        String username = authentication.getName();
        authService.logoutAllDevices(username);
        return ResponseEntity.ok("Logged out from all devices");
    }
}