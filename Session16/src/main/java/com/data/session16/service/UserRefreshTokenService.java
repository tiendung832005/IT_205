package com.data.session16.service;

import com.data.session16.model.entity.User;
import com.data.session16.model.entity.UserRefreshToken;
import com.data.session16.repository.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRefreshTokenService {
    private final UserRefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(User user, String token, String ipAddress) {
        UserRefreshToken refreshToken = new UserRefreshToken(null, user, token, ipAddress);
        refreshTokenRepository.save(refreshToken);
    }

    public boolean validateRefreshToken(String token, String ipAddress) {
        return refreshTokenRepository.findByTokenRefresh(token)
                .filter(rt -> rt.getIpAddress().equals(ipAddress))
                .isPresent();
    }
}
