package com.commerce.server.domain.token.util;

import com.commerce.server.domain.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.refresh_time}")
    private int refreshTime;
    @Value("${jwt.access_time}")
    private int accessTime;
    @Value("${jwt.secret}")
    private String  jwtSecret;
    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private String generateToken(UserEntity user,long tokenExpirationTime){

        long currentMillis = System.currentTimeMillis();

        Date issueAt = new Date(currentMillis);

        Date expiration = new Date(currentMillis + tokenExpirationTime);

        Map<String, Object> claims = new HashMap<>();

        claims.put("id",user.getId());
        claims.put("role",user.getRole());

        return Jwts
                .builder()
                .addClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
    public String generateAccessToken(UserEntity user){
        return generateToken(user,accessTime);
    }
    public String generateRefreshToken(UserEntity user){
        return generateToken(user,refreshTime);
    }
    private Claims extractClaimsFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getSubjectFromToken(String token){
        return extractClaimsFromToken(token).getSubject();
    }
    public Date getTokenExpiration(String token){
        return extractClaimsFromToken(token).getExpiration();
    }
    public boolean validateToken(String token){
        try {
            return getTokenExpiration(token).after(new Date()) && getSubjectFromToken(token) != null;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
