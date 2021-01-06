//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 12:42:23 PM BRST 
//


package org.omg.xmi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Replace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Replace">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/spec/XMI/20131001}Difference">
 *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="replacement" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Replace")
public class Replace
    extends Difference
{

    @XmlAttribute(name = "position")
    protected BigInteger position;
    @XmlAttribute(name = "replacement")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> replacement;

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPosition(BigInteger value) {
        this.position = value;
    }

    /**
     * Gets the value of the replacement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replacement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplacement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getReplacement() {
        if (replacement == null) {
            replacement = new ArrayList<Object>();
        }
        return this.replacement;
    }

}
