package com.example.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTGenerator {
    public static void main(String[] args) {
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Задаем время истечения токена (например, через 1 час)
        long expirationTimeInMillis = System.currentTimeMillis() + 3600000;

        // Генерируем токен
        String token = Jwts.builder()
                .setSubject("Yulia") // Устанавливаем имя пользователя в поле subject
                .setExpiration(new Date(expirationTimeInMillis)) // Устанавливаем время истечения токена
                .signWith(SignatureAlgorithm.HS512, secretKey) // Подписываем токен с использованием алгоритма HS512 и секретного ключа
                .compact();

        // Выводим сгенерированный токен
        System.out.println(token);

    }


}
