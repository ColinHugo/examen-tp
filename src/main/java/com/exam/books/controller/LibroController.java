package com.exam.books.controller;

import com.exam.books.dto.LibroRequest;
import com.exam.books.dto.LibroResponse;
import com.exam.books.exception.ApiError;
import com.exam.books.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Tag(
        name = "CRUD REST API para la gestión de Libros",
        description = "CRUD REST API para CREAR, ACTUALIZAR, OBTENER Y ELIMINAR libros"
)
@RequiredArgsConstructor
@RestController
@RequestMapping( "/libros" )
public class LibroController {

    private final LibroService  libroService;

    @Operation(
            summary = "Obtener todos los Libros",
            description = "REST API para obtener y paginar a todos los Libros que se encuentran en la base de datos. " +
                    "Título y Autor son opcionales, es indistinto a mayúsculas y acentos"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No hay Libros registrados",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @GetMapping
    public ResponseEntity< Page< LibroResponse> > findAll(
            @Parameter( description = "Título del Libro", example = "" )
            @RequestParam( required = false ) String titulo,
            @Parameter( description = "Autor", example = "sil" )
            @RequestParam( required = false ) String autor,
            @ParameterObject @PageableDefault( page = 0, size = 10 ) Pageable pageable ) {

        if ( StringUtils.hasText( titulo ) || StringUtils.hasText( autor ) ) {
            return ResponseEntity.ok( libroService.findByTituloOAutor( titulo, autor, pageable ) );
        }

        return ResponseEntity.ok( libroService.findAll( pageable ) );


    }

    @Operation(
            summary = "Obtener un Libro",
            description = "REST API para obtener un Libro por su UUID"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró el Libro",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @GetMapping( "/{id}" )
    public ResponseEntity< LibroResponse > findById(
            @Parameter( description = "UUID del Libro", example = "e0d1a5e8-12f7-4f6c-8b6c-f8123e7fd6cb" )
            @PathVariable UUID id ) {
        return ResponseEntity.ok( libroService.findById( id ) );
    }

    @Operation(
            summary = "Crear un Libro",
            description = "REST API para crear un nuevo Libro"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "201",
                    description = "Libro creado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Se envían datos no válidos",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Se intenta registrar un libro ya existente",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @PostMapping
    public ResponseEntity< LibroResponse > saveLibro( @Valid @RequestBody LibroRequest libroRequest ) {
        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( libroService.createLibro( libroRequest ) );
    }

    @Operation(
            summary = "Crear Libros de forma masiva",
            description = "REST API para crear múltiples Libros desde un archivo .csv"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "201",
                    description = "Libros creados"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Se envían datos no válidos",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe Autor del libro a registrar",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Se intenta registrar un libro ya existente",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @PostMapping( value = "/masivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity< String > loadLibros(
            @Parameter( description = "Archivo CSV con los datos de libros", required = true )
            @RequestParam MultipartFile archivo ) throws IOException {

        libroService.loadLibros( archivo );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( "Libros cargados con éxito" );

    }

    @Operation(
            summary = "Actualiza un Libro existente",
            description = "REST API para actualizar Libros a través de su UUID"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "Libro actualizado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Se envían datos no válidos",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe Autor del libro a actualizar o no existe el Libro",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Se intenta registrar un libro ya existente",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @PatchMapping( "/{id}" )
    public ResponseEntity< LibroResponse > updateLibro( @PathVariable UUID id, @RequestBody LibroRequest libroRequest ) {
        return ResponseEntity.ok( libroService.updateLibro( id, libroRequest ) );
    }

    @Operation(
            summary = "Elimina un Libro existente",
            description = "REST API para eliminar Libros a través de su UUID"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "204",
                    description = "Sin contenido, Libro eliminado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Se envían datos no válidos",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sin permiso",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe Libro a eliminar",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< Void > deleteLibro(
            @Parameter( description = "UUID del Libro", example = "e0d1a5e8-12f7-4f6c-8b6c-f8123e7fd6cb" )
            @PathVariable UUID id ) {
        libroService.deleteLibro( id );
        return ResponseEntity.noContent().build();
    }

}