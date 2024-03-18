package fr.campus.cda.charly.java_spring_boot_api.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtTokenUtils {
    public static final long JWT_TOKEN_VALIDITY = 3600 * 1000L; // 1 heure en millisecondes
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken() {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public Claims parseToken(String token) throws JwtException {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).build();
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (JwtException e) {
            throw new JwtException("Erreur lors du parsing du token JWT: " + e.getMessage());
        }
    }
}