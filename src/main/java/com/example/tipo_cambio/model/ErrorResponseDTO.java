package com.example.tipo_cambio.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String path;
    private String message;
    private Integer statusCode;
    private LocalDateTime localDateTime;
    private List<String> errorDetails;
}