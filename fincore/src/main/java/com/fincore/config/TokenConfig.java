package com.fincore.config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fincore.entities.User;

@Service
public class TokenConfig {
    
    private String passkey = "senha";
    private final Algorithm algorithm = Algorithm.HMAC256(passkey);

    public String generateToken(User user){
        return JWT.create()
        .withClaim("userId", user.getId())
        .withSubject(user.getUsername())
        .withExpiresAt(Instant.now().plusSeconds(86400))
        .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            return Optional.of(
                new JWTUserData(
                    decode.getClaim("userId").asLong(),
                    decode.getSubject()
                )
            );
        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }
}