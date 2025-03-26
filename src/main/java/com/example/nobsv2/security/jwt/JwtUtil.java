package com.example.nobsv2.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    public static String generateToken(User user) {
        return Jwts
            .builder()
            .subject(user.getUsername())
            .expiration(new Date(System.currentTimeMillis() + 300_000)) //5Min
            .signWith(getSigninKey())
            .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(getSigninKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public static boolean isTokenValid(String token) {
        // Can add more validation here
        return !isExpired(token);
    }

    public static boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private static SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode("BaconEggAndCheeseCheeseCheems");
        return Keys.hmacShaKeyFor(keyBytes);
    }   
}
