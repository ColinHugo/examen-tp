package com.exam.books.controller;

import com.exam.books.dto.AutorRequest;
import com.exam.books.dto.AutorResponse;
import com.exam.books.exception.ApiError;
import com.exam.books.service.AutorService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API para la gestión de Autores",
        description = "CRUD REST API para CREAR, ACTUALIZAR, OBTENER Y ELIMINAR autores"
)
@RequiredArgsConstructor
@RestController
@RequestMapping( "/autores" )
public class AutorController {

    private final AutorService autorService;

    @Operation(
            summary = "Obtener todos los Autores",
            description = "REST API para obtener y paginar a todos los Autores que se encuentran en la base de datos"
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
                    description = "No hay Autores registrados",
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
    public ResponseEntity< Page< AutorResponse > > findAll(
            @ParameterObject @PageableDefault( page = 0, size = 3 ) Pageable pageable ) {
        return ResponseEntity.ok( autorService.findAll( pageable ) );

    }

    @Operation(
            summary = "Crear un Autor",
            description = "REST API para crear un nuevo autor"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "201",
                    description = "Autor creado"
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
                    description = "Se intenta registrar un autor ya existente",
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
    public ResponseEntity< AutorResponse > saveAutor( @Valid @RequestBody AutorRequest autorRequest ) {
        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( autorService.createAutor( autorRequest ) );
    }

    @Operation(
            summary = "Actualizar un autor",
            description = "REST API para actualizar un autor existente a través de su id"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "Autor actualizado"
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
                    description = "No existe autor a actualizar",
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
    public ResponseEntity< AutorResponse > updateAutor(
            @Parameter( description = "Id del Autor", example = "8" )
            @PathVariable Long id, @Valid @RequestBody AutorRequest autorRequest ) {
        return ResponseEntity.ok( autorService.updateAutor( id, autorRequest ) );
    }

    @Operation(
            summary = "Eliminar un autor",
            description = "REST API para eliminar un Autor existente a través de su id"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "204",
                    description = "Sin contenido, Autor eliminado"
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
                    description = "No existe Autor a eliminar",
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
    public ResponseEntity< Void > deleteAutor(
            @Parameter( description = "Id del Autor", example = "7" )
            @PathVariable Long id ) {
        autorService.deleteAutor( id );
        return ResponseEntity.noContent().build();
    }

}