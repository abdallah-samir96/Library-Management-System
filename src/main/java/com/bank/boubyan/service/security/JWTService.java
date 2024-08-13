package com.bank.boubyan.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    private final static String SECRET_KEY = "d6dcb6ca9c96a5109f5382b2f22814d79afe1bd97c2be8167b5fb4b0e711b4ef";

    public String getUserEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Generate JWT token with 30 minutes of expiration
     * */
    public String generate(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 30) ))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Used to validate the token with the expiration date defined in extract all claims
     * */
    public boolean isValidToken(String token, UserDetails userDetails) {
        var userEmail = getUserEmail(token);
        return userEmail.contentEquals(userDetails.getUsername());
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().
                setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey() {
        byte []key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
