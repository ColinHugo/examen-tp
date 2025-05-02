
package com.exam.books.isbn;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IsValidISBN10Result" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isValidISBN10Result"
})
@XmlRootElement(name = "IsValidISBN10Response")
public class IsValidISBN10Response {

    @XmlElement(name = "IsValidISBN10Result")
    protected boolean isValidISBN10Result;

    /**
     * Obtiene el valor de la propiedad isValidISBN10Result.
     * 
     */
    public boolean isIsValidISBN10Result() {
        return isValidISBN10Result;
    }

    /**
     * Define el valor de la propiedad isValidISBN10Result.
     * 
     */
    public void setIsValidISBN10Result(boolean value) {
        this.isValidISBN10Result = value;
    }

}
