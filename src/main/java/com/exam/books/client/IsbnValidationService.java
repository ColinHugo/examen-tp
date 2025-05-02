package com.exam.books.client;

import com.exam.books.isbn.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@RequiredArgsConstructor
@Service
public class IsbnValidationService extends WebServiceGatewaySupport {

    private final WebServiceTemplate webServiceTemplate;

    public boolean validateIsbn13(String isbn) {
        IsValidISBN13 request = new IsValidISBN13();
        request.setSISBN(isbn);

        // Enviar la solicitud y obtener la respuesta
        IsValidISBN13Response response = (IsValidISBN13Response) webServiceTemplate.marshalSendAndReceive("http://webservices.daehosting.com/services/isbnservice.wso", request);
        return response.isIsValidISBN13Result();
    }

    public boolean validateIsbn10(String isbn) {
        IsValidISBN10 request = new IsValidISBN10();
        request.setSISBN(isbn);

        // Enviar la solicitud y obtener la respuesta
        IsValidISBN10Response response = (IsValidISBN10Response) webServiceTemplate.marshalSendAndReceive("http://webservices.daehosting.com/services/isbnservice.wso", request);
        return response.isIsValidISBN10Result();
    }

}