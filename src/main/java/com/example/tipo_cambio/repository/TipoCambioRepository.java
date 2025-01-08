package com.example.tipo_cambio.repository;

import com.example.tipo_cambio.model.TipoCambio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TipoCambioRepository extends ReactiveCrudRepository<TipoCambio, Long> {

    Mono<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
