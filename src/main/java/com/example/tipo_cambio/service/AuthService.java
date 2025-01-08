package com.example.tipo_cambio.service;

import com.example.tipo_cambio.model.JWTResponse;
import com.example.tipo_cambio.repository.UserRepository;
import com.example.tipo_cambio.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Mono<JWTResponse> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .filter(user -> user.getPassword().equals(password))  // Validación de contraseña
                .switchIfEmpty(Mono.error(new RuntimeException("Contraseña incorrecta")))
                .map(user -> {
                    String token = jwtTokenProvider.generateToken(user);
                    return new JWTResponse(token, "Bearer", user.getUsername(), user.getRoles().toString());
                })
                .onErrorResume(e -> {
                    // Maneja otros errores, como errores de base de datos o errores generales
                    return Mono.error(new RuntimeException("Error al autenticar al usuario", e));
                });
    }

}
