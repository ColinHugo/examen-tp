package com.exam.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WebServiceConfig {

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        webServiceTemplate.setDefaultUri("http://webservices.daehosting.com/services/isbnservice.wso");
        return webServiceTemplate;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.exam.books.isbn"); // Paquete donde están las clases generadas
        return marshaller;
    }

}