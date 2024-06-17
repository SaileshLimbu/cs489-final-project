package miu.edu.cs489finalproject.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String SECRET;

    public String generateToken(UserDetails userDetails, Long userId) {
        return Jwts.builder()
                .signWith(signInKey())
                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
                .claim("userId", userId)
                .subject(userDetails.getUsername())
                .issuer("miu.edu")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 24 * 60 * 60))
                .compact();
    }

    private Object populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private Key signInKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) signInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long extractUserId(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        if (token != null) {
            Claims claims = getClaimsFromToken(token);
            return claims.get("userId", Long.class);
        }
        return null;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
