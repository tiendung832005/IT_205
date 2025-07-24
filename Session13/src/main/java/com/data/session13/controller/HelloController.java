package com.data.session13.controller;

import com.data.session13.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                return "Hello, " + jwtUtil.extractUsername(token);
            }
        }
        throw new RuntimeException("Unauthorized");
    }
}
