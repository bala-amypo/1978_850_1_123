// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     @Value("${app.jwtSecret}")
//     private String jwtSecret;

//     @Value("${app.jwtExpirationMs}")
//     private long jwtExpirationMs;

//     // generate token with userId, email, role
//     public String generateToken(Long userId, String email, String role) {
//         Date now = new Date();
//         Date expiry = new Date(now.getTime() + jwtExpirationMs);

//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .claim("role", role)
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                 .compact();
//     }

//     // generate token with username only (backward compatibility)
//     public String generateToken(String username) {
//         return generateToken(1L, username, "USER");
//     }

//     // get username from token
//     public String getUsernameFromJwt(String token) {
//         return getClaims(token).getSubject();
//     }

//     // get email from token
//     public String getEmailFromToken(String token) {
//         return getClaims(token).get("email", String.class);
//     }

//     // get userId from token
//     public Long getUserIdFromToken(String token) {
//         return getClaims(token).get("userId", Long.class);
//     }

//     // get role from token
//     public String getRoleFromToken(String token) {
//         return getClaims(token).get("role", String.class);
//     }

//     // get claims
//     public Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(jwtSecret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     // validate token
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//             return true;
//         } catch (SignatureException | MalformedJwtException |
//                  ExpiredJwtException | UnsupportedJwtException |
//                  IllegalArgumentException ex) {
//             return false;
//         }
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_MS = 3600000; // 1 hour

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
            .setSubject(email)
            .claim("userId", userId)
            .claim("role", role.toUpperCase())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }
}
