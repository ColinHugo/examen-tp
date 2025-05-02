package com.exam.books.exception;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( ObjectNotFoundException.class )
    public ResponseEntity< ApiError > handleObjectNotFoundException( ObjectNotFoundException exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Recurso no encontrado: " + exception.getMessage() )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( apiError );

    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity< ApiError > handleInvalidArguments( MethodArgumentNotValidException exception, HttpServletRequest request ) {

        Map< String, String > errors = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach( error -> {

                    String nameField = error.getField();
                    String errorMessage = error.getDefaultMessage();

                    errors.put( nameField, errorMessage );

                } );

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Se encontraron errores en los siguientes campos: " + errors )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Descripción del error: " + apiError );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( apiError );

    }

    @ExceptionHandler( DataIntegrityViolationException.class )
    public ResponseEntity< ApiError > handleDataIntegrityViolationException( DataIntegrityViolationException exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Elemento duplicado" )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.CONFLICT ).body( apiError );

    }

    @ExceptionHandler( MultipartException.class )
    public ResponseEntity< ApiError > handleGenericException( MultipartException exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Favor de cargar un archivo: " + exception.getMessage() )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( apiError );

    }

    @ExceptionHandler( CsvRequiredFieldEmptyException.class )
    public ResponseEntity< ApiError > handleGenericException( CsvRequiredFieldEmptyException exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Favor de verificar el contenido del archivo: " + exception.getMessage() )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( apiError );

    }

    @ExceptionHandler( InvalidFileException.class )
    public ResponseEntity< ApiError > handleInvalidFileException( InvalidFileException exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Error de formato: " + exception.getMessage() )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( apiError );

    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity< ApiError > handleGenericException( Exception exception, HttpServletRequest request ) {

        ApiError apiError = ApiError
                .builder()
                .backendMessage( exception.getLocalizedMessage() )
                .url( request.getRequestURL().toString() )
                .method( request.getMethod() )
                .message( "Error interno del sistema: " + exception.getMessage() )
                .timestamp( LocalDateTime.now() )
                .build();

        log.error( "Detalles del error " + exception.getMessage() );

        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( apiError );

    }

}