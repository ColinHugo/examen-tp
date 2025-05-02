package com.exam.books.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LibroCsvDto {

    @CsvBindByName( column = "titulo", required = true )
    private String titulo;

    @CsvBindByName( column = "autor_id", required = true )
    private Long autorId;

    @CsvBindByName( column = "numero_paginas", required = true )
    private int numeroPaginas;

    @CsvBindByName( column = "isbn", required = true )
    private String isbn;

}