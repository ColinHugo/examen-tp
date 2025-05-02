package com.exam.books.service;

import com.exam.books.dto.AutorRequest;
import com.exam.books.dto.AutorResponse;
import com.exam.books.model.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AutorService {

    Page< AutorResponse > findAll( Pageable pageable );

    AutorResponse createAutor( AutorRequest autorRequest );

    AutorResponse updateAutor( Long id, AutorRequest autorRequest );

    void deleteAutor( Long id );

}