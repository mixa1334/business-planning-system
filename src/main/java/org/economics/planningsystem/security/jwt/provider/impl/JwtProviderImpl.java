package org.economics.planningsystem.security.jwt.provider.impl;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProviderImpl implements JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expirationInMillisecond;
    private Key secretKey;
    private JwtParser jwtParser;

    @PostConstruct
    private void initMethod() {
        byte[] secretBytes = Base64.getDecoder().decode(secret);
        secretKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    @Override
    public String generateToken(String login) {
        Date currentDate = Date.from(Instant.now());
        Instant expirationInstant = Instant.now().plus(expirationInMillisecond, ChronoUnit.MILLIS);
        Date expirationDate = Date.from(expirationInstant);

        return Jwts.builder()
                .setSubject(login)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String getLoginFromToken(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
