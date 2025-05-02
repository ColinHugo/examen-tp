package com.exam.books.client;

import com.exam.books.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Validación de ISBN",
        description = "Servicio SOAP para la validación de ISBN 10 y 13"
)
@RequiredArgsConstructor
@RestController
public class IsbnController {

    private final IsbnValidationService isbnValidationService;

    @Operation(
            summary = "Validar ISBN 13",
            description = "Servicio SOAP para determinar si el ISBN 13 es válido"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @GetMapping( "/validateIsbn13/{isbn}" )
    public boolean validateIsbn13(
            @Parameter( description = "ISBN 13", example = "978-0-306-40615-7" )
            @PathVariable String isbn ) {
        return isbnValidationService.validateIsbn13( isbn );
    }

    @Operation(
            summary = "Validar ISBN 10",
            description = "Servicio SOAP para determinar si el ISBN 10 es válido"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno en el servidor",
                    content = @Content(
                            schema = @Schema( implementation = ApiError.class )
                    )
            )
    } )
    @GetMapping("/validateIsbn10/{isbn}")
    public boolean validateIsbn10(
            @Parameter( description = "ISBN 1o", example = "0-306-40615-2" )
            @PathVariable String isbn ) {
        return isbnValidationService.validateIsbn10( isbn );
    }

}