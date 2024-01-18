package com.sistema.examenes.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;
    //generar token
    public String generateToken(User userjwt){
        List<String> roles = userjwt.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());


        Map<String, Object> extra = new HashMap<>();
        extra.put("roles", roles);
        return Jwts.builder()
                .subject(userjwt.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpiration)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .claims(extra)
                .compact();
    }
    //validar el token  (prueba)
    public boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (ExpiredJwtException e){
            System.out.println("token a expirado");
            return false;
        }
    }
    //obtener claims
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // obtener un solo claims
    public <T> T getClaim(String token, Function<Claims,T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    // obtener el claim username
    public String getUsername(String token){
        return getClaim(token, Claims::getSubject);
    }


}
