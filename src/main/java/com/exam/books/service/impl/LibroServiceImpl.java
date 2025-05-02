package com.exam.books.service.impl;

import com.exam.books.dto.LibroCsvDto;
import com.exam.books.dto.LibroRequest;
import com.exam.books.dto.LibroResponse;
import com.exam.books.exception.InvalidFileException;
import com.exam.books.exception.ObjectNotFoundException;
import com.exam.books.model.Autor;
import com.exam.books.model.Libro;
import com.exam.books.repository.AutorRepository;
import com.exam.books.repository.LibroRepository;
import com.exam.books.service.LibroService;
import com.exam.books.utils.TextoUtils;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LibroServiceImpl implements LibroService {

    private final AutorRepository autorRepository ;
    private final LibroRepository libroRepository;

    @Override
    public Page< LibroResponse > findAll( Pageable pageable ) {

        return libroRepository
                .findAll( pageable )
                .map( this::mapLibroToResponse );

    }

    @Override
    public LibroResponse findById( UUID id ) {

        Libro libro = libroRepository
                .findById( id )
                .orElseThrow( () -> new ObjectNotFoundException( "Libro no encontrado con id: " +  id ) );

        return mapLibroToResponse( libro );

    }

    @Override
    public Page< LibroResponse > findByTituloOAutor( String titulo, String autor, Pageable pageable ) {

        titulo = TextoUtils.normalizar( titulo );
        autor = TextoUtils.normalizar( autor );

        Page< Libro > libros = libroRepository.findByTituloYAutor( titulo, autor, pageable );

        return libros
                .map(libro -> LibroResponse
                        .builder()
                        .id( libro.getId() )
                        .titulo( libro.getTitulo() )
                        .autor( libro.getAutor().getNombre() )
                        .numeroPaginas( libro.getNumeroPaginas() )
                        .urlPortada( libro.getUrlPortada() )
                        .build()
                );

    }

    @Override
    public LibroResponse createLibro( LibroRequest libroRequest ) {

        Autor autor = autorRepository
                .findById( libroRequest.autorId() )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede crear libro, autor no encontrado con id: " +  libroRequest.autorId() ) );

        Libro libro = Libro
                .builder()
                .titulo( libroRequest.titulo() )
                .numeroPaginas( libroRequest.numeroPaginas() )
                .urlPortada( libroRequest.urlPortada() )
                .autor( autor )
                .build();

        Libro saved = libroRepository.save( libro );

        return mapLibroToResponse( saved );

    }

    @Override
    public LibroResponse updateLibro( UUID id, LibroRequest libroRequest ) {

        Autor autor = autorRepository
                .findById( libroRequest.autorId() )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede actualizar libro, autor no encontrado con id: " +  libroRequest.autorId() ) );

        Libro libroToUpdate = libroRepository.findById( id )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede actualizar libro, libro no encontrado con id: " + id ) );

        libroToUpdate.setTitulo( libroRequest.titulo() );
        libroToUpdate.setAutor( autor );
        libroToUpdate.setNumeroPaginas( libroRequest.numeroPaginas() );
        libroToUpdate.setUrlPortada( libroRequest.urlPortada() );

        Libro libro = libroRepository.save( libroToUpdate );

        return mapLibroToResponse( libro );

    }

    @Override
    public void loadLibros( MultipartFile inputStream ) throws IOException {

        String filename = inputStream.getOriginalFilename();

        if ( filename != null && !filename.endsWith( ".csv" ) ) {
            throw new InvalidFileException( "No es un archivo con extensión csv" );
        }

        List< LibroCsvDto > rows = new CsvToBeanBuilder< LibroCsvDto >( new InputStreamReader( inputStream.getInputStream() ) )
                .withType( LibroCsvDto.class )
                .withIgnoreLeadingWhiteSpace( true )
                .build()
                .parse();

        List< Libro > libros = new ArrayList<>();

        for ( LibroCsvDto row : rows ) {

            Autor autor = autorRepository.findById( row.getAutorId() )
                    .orElseThrow( () -> new IllegalArgumentException( "Error en carga masiva, autor no encontrado: " + row.getAutorId() ) );

            Libro libro = new Libro();
            libro.setTitulo( row.getTitulo() );
            libro.setAutor( autor );
            libro.setNumeroPaginas( row.getNumeroPaginas() );
            libro.setUrlPortada( row.getUrlPortada() );

            libros.add( libro );

        }

        libroRepository.saveAll( libros );

    }


    @Override
    public void deleteLibro( UUID id ) {

        Libro libro = libroRepository
                .findById( id )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede eliminar, libro no encontrado con id: " + id ) );

        libroRepository.delete( libro );

    }

    private LibroResponse mapLibroToResponse( Libro libro ) {

        return LibroResponse
                .builder()
                .id( libro.getId() )
                .titulo( libro.getTitulo() )
                .autor( libro.getAutor().getNombre() )
                .numeroPaginas( libro.getNumeroPaginas() )
                .urlPortada( libro.getUrlPortada() )
                .build();

    }

}