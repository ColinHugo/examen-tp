package com.exam.books.model;

import com.exam.books.utils.TextoUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString( exclude = "autor" )
@Builder
@Table( name = "libros" )
@Entity
public class Libro {

    @Id
    @GeneratedValue( generator = "UUID" )
    private UUID id;

    @Column( unique = true )
    private String titulo;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "autor_id", nullable = false )
    private Autor autor;
    private int numeroPaginas;
    private String isbn;
    private String urlPortada;

    @PrePersist
    @PreUpdate
    public void normalizarTextos() {
        this.titulo = TextoUtils.normalizar( this.titulo );
    }

}