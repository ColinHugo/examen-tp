package com.exam.books.model;

import com.exam.books.utils.TextoUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table( name = "autores" )
@Entity
public class Autor {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true )
    private String nombre;

    private LocalDate fechaNacimiento;

    @PrePersist
    @PreUpdate
    public void normalizarTextos() {
        this.nombre = TextoUtils.normalizar( this.nombre );
    }

}