package com.example.tipo_cambio.controller;

import com.example.tipo_cambio.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    @Autowired
    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @PostMapping("/convertir")
    public Mono<ResponseEntity<Double>> convertirMoneda(@RequestParam Double monto,
                                                        @RequestParam String monedaOrigen,
                                                        @RequestParam String monedaDestino) {
        return tipoCambioService.convertirMoneda(monto, monedaOrigen, monedaDestino)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
