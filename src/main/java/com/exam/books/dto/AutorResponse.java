package com.exam.books.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AutorResponse(
        Long id,
        String nombre,
        LocalDate fechaNacimiento
) {}