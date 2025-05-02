package com.exam.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
public record AutorRequest (

        @Schema( description = "Nombre completo del autor", example = "James Stewart" )
        @NotNull( message = "El nombre es obligatorio" )
        @NotBlank( message = "El nombre no puede ir en blanco" )
        @Size( min = 1, max = 100 )
        String nombre,

        @Schema( description = "Fecha de nacimiento del autor", example = "1941-03-29" )
        @NotNull( message = "La fecha de nacimiento es obligatoria" )
        @Past( message = "La fecha de nacimiento debe ser en el pasado" )
        LocalDate fechaNacimiento
) {}