package com.SG.Stealth.Garage.API.config.security;

import com.SG.Stealth.Garage.API.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("stealth_garage_api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT ", e);
        }
    }

    private Instant dateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
