package com.exam.books.utils;

import org.apache.commons.lang3.StringUtils;

public class TextoUtils {

    public static String normalizar( String input ) {

        if ( input == null ) {
            return null;
        }

        String texto = StringUtils.stripAccents( input );
        texto = texto
                .replaceAll( "[^A-Za-z ]", "" )
                .replaceAll( "\\s+", " " )
                .trim()
                .toUpperCase();

        return texto;

    }

}