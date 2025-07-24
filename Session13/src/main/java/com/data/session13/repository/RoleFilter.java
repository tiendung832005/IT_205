package com.data.session13.repository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class RoleFilter extends OncePerRequestFilter {
    private final String SECRET_KEY = "321321";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
                String role = claims.get("role", String.class);

                String path = request.getRequestURI();
                if (path.startsWith("/api/admin") && !"Admin".equals(role)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for non-admin users");
                    return;
                } else if (path.startsWith("/api/editor") && !"Editor".equals(role) && !"Admin".equals(role)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for non-editor users");
                    return;
                } else if (path.startsWith("/api/user") && !"User".equals(role) && !"Editor".equals(role) && !"Admin".equals(role)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for non-user roles");
                    return;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
