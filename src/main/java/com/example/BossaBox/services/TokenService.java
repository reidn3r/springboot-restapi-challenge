package com.example.BossaBox.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.BossaBox.domain.User.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")

    private String secret;
    public String generateToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("BossaBox-API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDateGenerator())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("JWT Error: ", e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("BossaBox-API")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            return "";
        }
    }
    private Instant expirationDateGenerator(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }
}
