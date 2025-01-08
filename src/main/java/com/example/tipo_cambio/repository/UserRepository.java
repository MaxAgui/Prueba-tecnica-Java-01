package com.example.tipo_cambio.repository;

import com.example.tipo_cambio.model.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<Usuario, Long> {
    Mono<Usuario> findByUsername(String username);
}