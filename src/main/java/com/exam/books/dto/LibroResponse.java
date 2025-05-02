package com.exam.books.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LibroResponse (
        UUID id,
        String titulo,
        String autor,
        int numeroPaginas,
        String urlPortada
) {
}