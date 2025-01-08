package com.example.tipo_cambio.service;

import com.example.tipo_cambio.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class TipoCambioService {
    private final TipoCambioRepository tipoCambioRepository;

    @Autowired
    public TipoCambioService(TipoCambioRepository tipoCambioRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
    }

    public Mono<Double> convertirMoneda(Double monto, String monedaOrigen, String monedaDestino) {
        return tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
                .map(tipoCambio -> monto * tipoCambio.getTipoCambio())  // Realiza la conversión
                .switchIfEmpty(Mono.error(new RuntimeException("Tipo de cambio no encontrado")));
    }
}
