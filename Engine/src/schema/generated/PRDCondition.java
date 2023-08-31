//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.08.31 at 02:32:28 PM IDT 
//


package schema.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}PRD-condition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="singularity" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="multiple"/>
 *             &lt;enumeration value="single"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="operator">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="!="/>
 *             &lt;enumeration value="="/>
 *             &lt;enumeration value="bt"/>
 *             &lt;enumeration value="lt"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="logical">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="and"/>
 *             &lt;enumeration value="or"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="entity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prdCondition"
})
@XmlRootElement(name = "PRD-condition")
public class PRDCondition {

    @XmlElement(name = "PRD-condition")
    protected List<PRDCondition> prdCondition;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "singularity", required = true)
    protected String singularity;
    @XmlAttribute(name = "operator")
    protected String operator;
    @XmlAttribute(name = "property")
    protected String property;
    @XmlAttribute(name = "logical")
    protected String logical;
    @XmlAttribute(name = "entity")
    protected String entity;

    /**
     * Gets the value of the prdCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prdCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRDCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRDCondition }
     * 
     * 
     */
    public List<PRDCondition> getPRDCondition() {
        if (prdCondition == null) {
            prdCondition = new ArrayList<PRDCondition>();
        }
        return this.prdCondition;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the singularity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingularity() {
        return singularity;
    }

    /**
     * Sets the value of the singularity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingularity(String value) {
        this.singularity = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperator(String value) {
        this.operator = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperty() {
        return property;
    }

    /**
     * Sets the value of the property property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProperty(String value) {
        this.property = value;
    }

    /**
     * Gets the value of the logical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogical() {
        return logical;
    }

    /**
     * Sets the value of the logical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogical(String value) {
        this.logical = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntity(String value) {
        this.entity = value;
    }

}
