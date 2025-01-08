package com.example.tipo_cambio.security;

import com.example.tipo_cambio.model.ErrorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (!isValidAuthorizationHeader(request)) {
                chain.doFilter(request, response);
                return;
            }

            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            handleJwtException(response, request, "TOKEN VENCIDO", HttpStatus.UNAUTHORIZED);
        } catch (SignatureException | MalformedJwtException e) {
            handleJwtException(response, request, "TOKEN_ERROR_FIRMA", HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isValidAuthorizationHeader(HttpServletRequest request) {
        String authorizationHeader = getAuthorizationHeader(request);
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }

    private String extractJwtFromHeader(HttpServletRequest request) {
        String authorizationHeader = getAuthorizationHeader(request);
        return authorizationHeader != null ? authorizationHeader.substring(7) : null;
    }

    private String getAuthorizationHeader(HttpServletRequest request){
        return request.getHeader("Authorization");
    }


    private void handleJwtException(HttpServletResponse response, HttpServletRequest request, String error, HttpStatus status) throws IOException {
        sendErrorResponse(response, request, error, status);
    }

    private void sendErrorResponse(HttpServletResponse response, HttpServletRequest request, String error, HttpStatus status) throws IOException {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .path(request.getRequestURI())
                .message(error)
                .statusCode(status.value())
                .localDateTime(LocalDateTime.now())
                .errorDetails(null)
                .build();
        response.setStatus(status.value());
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}