package com.exam.books.service.impl;

import com.exam.books.dto.AutorRequest;
import com.exam.books.dto.AutorResponse;
import com.exam.books.exception.ObjectNotFoundException;
import com.exam.books.model.Autor;
import com.exam.books.repository.AutorRepository;
import com.exam.books.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository ;

    @Override
    public Page< AutorResponse > findAll( Pageable pageable ) {
        return autorRepository
                .findAll( pageable )
                .map( this::mapToAutorResponse );
    }

    @Override
    public AutorResponse createAutor( AutorRequest autorRequest ) {

        Autor autor = Autor
                .builder()
                .nombre( autorRequest.nombre() )
                .fechaNacimiento( autorRequest.fechaNacimiento() )
                .build();

        Autor saved = autorRepository.save( autor );

        return mapToAutorResponse( saved );
    }

    @Override
    public AutorResponse updateAutor( Long id, AutorRequest autorRequest ) {

        Autor autorToUpdate = autorRepository
                .findById( id )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede actualizar, autor no encontrado con id: " +  id ) );

        autorToUpdate.setNombre( autorRequest.nombre() );
        autorToUpdate.setFechaNacimiento( autorRequest.fechaNacimiento() );

        Autor autor = autorRepository.save( autorToUpdate );

        return mapToAutorResponse( autor );

    }

    @Override
    public void deleteAutor( Long id ) {

        Autor autor = autorRepository
                .findById( id )
                .orElseThrow( () -> new ObjectNotFoundException( "No se puede eliminar, autor no encontrado con id: " + id ) );

        autorRepository.delete( autor );

    }

    private AutorResponse mapToAutorResponse( Autor autor ) {
        return AutorResponse
                .builder()
                .id( autor.getId() )
                .nombre( autor.getNombre() )
                .fechaNacimiento( autor.getFechaNacimiento() )
                .build();
    }

}