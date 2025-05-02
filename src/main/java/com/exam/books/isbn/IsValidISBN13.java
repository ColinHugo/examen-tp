
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
 *         &lt;element name="sISBN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "sisbn"
})
@XmlRootElement(name = "IsValidISBN13")
public class IsValidISBN13 {

    @XmlElement(name = "sISBN", required = true)
    protected String sisbn;

    /**
     * Obtiene el valor de la propiedad sisbn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSISBN() {
        return sisbn;
    }

    /**
     * Define el valor de la propiedad sisbn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSISBN(String value) {
        this.sisbn = value;
    }

}
