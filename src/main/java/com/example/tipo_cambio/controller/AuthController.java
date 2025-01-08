package com.example.tipo_cambio.controller;

import com.example.tipo_cambio.model.JWTResponse;
import com.example.tipo_cambio.model.LoginRequest;
import com.example.tipo_cambio.service.AuthService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Mono<JWTResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
