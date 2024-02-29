package com.example.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTValidator {
    public static void main(String[] args) {

        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJZdWxpYSIsImV4cCI6MTcwOTIxMDI0OX0._8kEofKiBnCsx5aI5VqCPTp5rULdClHjSmZQqN7UA-TWEavdThjeiSroVghPV3xB57wmP3XWjeouHZntPYq0ZA";


        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
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
