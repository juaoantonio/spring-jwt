package br.com.joaobarbosa.springjwt.modules.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    SecretKey SECRET = Keys.hmacShaKeyFor("______________secretkey_______________".getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)  // subject se refere ao usuario pelo username
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1h
                .signWith(SECRET)  // algoritmo de assinatura
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET)  // algoritmo de assinatura
                .build()
                .parseSignedClaims(token) // valida o token
                .getPayload()
                .getSubject();  // retorna o username
    }
}
