package com.exam.books.repository;

import com.exam.books.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LibroRepository extends JpaRepository< Libro, Long > {

    Optional< Libro > findById( UUID id );

    @Query("""
    SELECT l FROM Libro l
    JOIN FETCH l.autor a
    WHERE ( :titulo IS NULL OR l.titulo LIKE %:titulo% )
    AND ( :autor IS NULL OR a.nombre LIKE %:autor% )
    """)
    Page< Libro > findByTituloYAutor( String titulo, String autor, Pageable pageable );

}