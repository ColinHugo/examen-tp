package com.exam.books.service;

import com.exam.books.dto.LibroRequest;
import com.exam.books.dto.LibroResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface LibroService {

    Page< LibroResponse > findAll( Pageable pageable );

    LibroResponse findById( UUID id );

    Page< LibroResponse > findByTituloOAutor( String titulo, String autor, Pageable pageable );

    LibroResponse createLibro( LibroRequest libroRequest );

    LibroResponse updateLibro( UUID id, LibroRequest libroRequest );

    void loadLibros( MultipartFile inputStream ) throws IOException;

    void deleteLibro( UUID id );

}