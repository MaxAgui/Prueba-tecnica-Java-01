package com.example.tipo_cambio.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class TipoCambio {

    @Id
    private Long id;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
}
