package com.sherwin.restaurant_review_platform.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

  public final JwtEncoder jwtEncoder;
  public final JwtDecoder jwtDecoder;

  public String generateJwt(Authentication authentication) {

    Instant now = Instant.now();
    Instant expiry = now.plus(1, ChronoUnit.HOURS);

    String scope = authentication.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(expiry)
        .subject(authentication.getName())
        .claim("roles", scope)
        .build();

    String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return token;
  }

}
