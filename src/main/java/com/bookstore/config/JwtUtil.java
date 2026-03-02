package com.bookstore.config;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key (Minimum 256-bit for HS256)
    private static final String SECRET = "my_super_secret_key_for_bookstore_application_2026_secure";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate Token
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username) // Who is the user
                .claim("role", role) // Add custom claim
                .setIssuedAt(new Date()) // Token created time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // Sign token
                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract Role
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Extract All Claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
