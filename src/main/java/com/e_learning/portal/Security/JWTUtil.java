package com.e_learning.portal.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private final String SECRET_KEY = "g0AhZt5oMVRvGLHzHwTRk0fQKItNzZbKTeiyZ9MHZsQ=";
    private final int EXPERATION_MS = 1000*60*60*24;

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+ EXPERATION_MS))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token){
        return extractAllClaims(token)
                .getSubject();
    }

    public boolean isValidToken(String token){
        try {
            extractAllClaims(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
