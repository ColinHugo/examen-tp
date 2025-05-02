package com.exam.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LibroRequest (

        @Schema( description = "ID del Autor", example = "8" )
        @Positive( message = "Ingresa un número válido" )
        Long autorId,

        @Schema( description = "Título del Libro", example = "Cálculo de varias variables" )
        @NotNull( message = "El título es obligatorio" )
        @NotBlank( message = "El título no puede ir en blanco" )
        @Size( min = 1, max = 100 )
        String titulo,

        @Schema( description = "Número de páginas del Libro", example = "586" )
        @Positive( message = "Ingresa un número válido" )
        int numeroPaginas,

        @Schema( description = "URL de la portada del Libro", example = "https://example.com/calculo.jpg" )
        @NotNull( message = "La URL de la portada es obligatoria" )
        @NotBlank( message = "La URL no puede ir en blanco" )
        @Size( min = 1, max = 100 )
        String urlPortada

) {}