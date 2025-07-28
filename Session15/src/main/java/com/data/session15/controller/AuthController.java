package com.data.session15.controller;

import com.data.session15.model.dto.LoginRequest;
import com.data.session15.model.dto.UserDto;
import com.data.session15.security.jwt.JwtResponse;
import com.data.session15.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
