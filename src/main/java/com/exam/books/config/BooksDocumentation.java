package com.exam.books.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Books & Autors REST API Documentation",
                description = "Documentación REST API para la gestión de libros y autores",
                version = "v1",
                contact = @Contact(
                        name = "Hugo Colín",
                        email = "hcolin@correo.com",
                        url = "https://www.totalplay.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.totalplay.com/doc"
                )
        ),
        servers = {
                @Server(
                        description = "Servidor para ejecutar los endpoints",
                        url = "http://localhost:8080"
                )
        }
)
@Configuration
public class BooksDocumentation {
}