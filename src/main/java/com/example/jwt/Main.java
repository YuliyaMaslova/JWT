package com.example.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        long expirationTimeInMillis = System.currentTimeMillis() + 3600000;

        String token = Jwts.builder()
                .setSubject("Yulia")
                .setExpiration(new Date(expirationTimeInMillis))
                .signWith(secretKey)
                .compact();

        System.out.println(token);

        Validator.validateToken(token, secretKey);
    }
}

class Validator {
    public static void validateToken(String token, Key secretKey) {
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            Jws<Claims> jwsClaims = parser.parseClaimsJws(token);
            Claims claims = jwsClaims.getBody();

            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();

            boolean isValid = !expirationDate.before(new Date());

            System.out.println("Username: " + username);
            System.out.println("Expiration Date: " + expirationDate);
            System.out.println("Token is valid: " + isValid);


        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
    }
}

