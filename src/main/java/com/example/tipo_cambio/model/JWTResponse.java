package com.example.tipo_cambio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String roles;

}
