package com.data.session13.security.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTProvider {
    @Value("${jwt_secret}")
    private String jwtSecret;
    @Value("${jwt_expire}")
    private int jwtExpire;
    @Value("${jwt_refresh}")
    private int jwtRefresh;

    public String generateToken(String username){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpire))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            log.error("JWT token expired!");
        }catch (UnsupportedJwtException e){
            log.error("JWT token unsupported!");
        }catch (MalformedJwtException e){
            log.error("JWT token malformed!");
        }catch (SignatureException e){
            log.error("JWT token signature error!");
        }catch (IllegalArgumentException e){
            log.error("JWT token argument error!");
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String refreshToken(String token, String username){
        if(validateToken(token) && getUsernameFromToken(token).equals(username)){
            Date now = new Date();
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + jwtRefresh))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }
        return null;
    }
}
