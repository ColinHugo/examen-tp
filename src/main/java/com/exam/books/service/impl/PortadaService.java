package com.exam.books.service.impl;

import com.exam.books.client.IsbnValidationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PortadaService {

    private final WebClient webClient;

    private final IsbnValidationService isbnValidationService;

    public PortadaService( WebClient.Builder builder, IsbnValidationService isbnValidationService ) {
        this.webClient = builder.baseUrl( "https://www.googleapis.com/books/v1" ).build();
        this.isbnValidationService = isbnValidationService;
    }

    public String obtenerUrlPortada( String isbn ) {

        if ( !StringUtils.hasText( isbn ) ) {
            return "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";
        }

        if ( isbnValidationService.validateIsbn10( isbn ) || isbnValidationService.validateIsbn13( isbn ) ) {

            String url = "/volumes?q=isbn:" + isbn;

            JsonNode response = webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if ( response.has( "items" ) ) {

                JsonNode volumeInfo = response
                        .get( "items" )
                        .get( 0 )
                        .get( "volumeInfo" );

                JsonNode imageLinks = volumeInfo.get( "previewLink" );

                if ( imageLinks != null ) {
                    return imageLinks.asText();
                }
            }

        }

        return "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";

    }

}