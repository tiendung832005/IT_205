package com.data.session15.service;

import com.data.session15.model.dto.LoginRequest;
import com.data.session15.model.dto.UserDto;
import com.data.session15.model.entity.User;
import com.data.session15.repository.RoleRepository;
import com.data.session15.repository.UserRepository;
import com.data.session15.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;


    public void register(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role user not found")));
        userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtUtils.generateToken(user.getUsername());
    }

}