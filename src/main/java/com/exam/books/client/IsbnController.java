package com.exam.books.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IsbnController {

    private final IsbnValidationService isbnValidationService;

    @GetMapping("/validateIsbn13/{isbn}")
    public boolean validateIsbn13( @PathVariable String isbn ) {
        return isbnValidationService.validateIsbn13( isbn );
    }

    @GetMapping("/validateIsbn10/{isbn}")
    public boolean validateIsbn10( @PathVariable String isbn ) {
        return isbnValidationService.validateIsbn10( isbn );
    }

}