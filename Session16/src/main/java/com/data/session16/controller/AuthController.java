package com.data.session16.controller;

import com.data.session16.model.entity.*;
import com.data.session16.security.jwt.JwtUtil;
import com.data.session16.service.UserRefreshTokenService;
import com.data.session16.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        User user = userService.login(request.getUsername(), request.getPassword());
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        refreshTokenService.saveRefreshToken(user, refreshToken, httpRequest.getRemoteAddr());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {
        if (refreshTokenService.validateRefreshToken(request.getRefreshToken(), httpRequest.getRemoteAddr())) {
            String newAccessToken = jwtUtil.generateAccessToken(request.getUser());
            return ResponseEntity.ok(new AuthResponse(newAccessToken, request.getRefreshToken()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token or IP address");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        userService.logout(request.getUsername());
        return ResponseEntity.ok("Logged out successfully");
    }
}
