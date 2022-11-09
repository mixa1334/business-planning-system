package org.economics.planningsystem.security.jwt.provider;

import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);
}
