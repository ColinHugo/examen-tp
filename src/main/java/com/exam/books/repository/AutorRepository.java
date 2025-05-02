package com.exam.books.repository;

import com.exam.books.model.Autor;
import com.exam.books.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository< Autor, Long > {
}