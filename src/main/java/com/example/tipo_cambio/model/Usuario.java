package com.example.tipo_cambio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter
public class Usuario {

    @Id
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    private List<Role> roles;
}
