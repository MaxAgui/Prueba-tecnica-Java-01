package com.example.tipo_cambio.security;

import com.example.tipo_cambio.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {

    private final String secretKey = "secret"; // Usa un valor más seguro en producción
    private final long validityInMilliseconds = 3600000; // 1 hora

    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
