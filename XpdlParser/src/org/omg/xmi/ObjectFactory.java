//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 12:42:23 PM BRST 
//


package org.omg.xmi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.omg.xmi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Difference_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "difference");
    private final static QName _Replace_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "Replace");
    private final static QName _Extension_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "Extension");
    private final static QName _Add_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "Add");
    private final static QName _Delete_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "Delete");
    private final static QName _XMI_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "XMI");
    private final static QName _Documentation_QNAME = new QName("http://www.omg.org/spec/XMI/20131001", "documentation");
    private final static QName _DocumentationLongDescription_QNAME = new QName("", "longDescription");
    private final static QName _DocumentationOwner_QNAME = new QName("", "owner");
    private final static QName _DocumentationExporter_QNAME = new QName("", "exporter");
    private final static QName _DocumentationExporterVersion_QNAME = new QName("", "exporterVersion");
    private final static QName _DocumentationContact_QNAME = new QName("", "contact");
    private final static QName _DocumentationShortDescription_QNAME = new QName("", "shortDescription");
    private final static QName _DocumentationNotice_QNAME = new QName("", "notice");
    private final static QName _DocumentationTimestamp_QNAME = new QName("", "timestamp");
    private final static QName _DifferenceContainer_QNAME = new QName("", "container");
    private final static QName _DifferenceDifference_QNAME = new QName("", "difference");
    private final static QName _DifferenceTarget_QNAME = new QName("", "target");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.omg.xmi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Difference }
     * 
     */
    public Difference createDifference() {
        return new Difference();
    }

    /**
     * Create an instance of {@link Extension }
     * 
     */
    public Extension createExtension() {
        return new Extension();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link Replace }
     * 
     */
    public Replace createReplace() {
        return new Replace();
    }

    /**
     * Create an instance of {@link XMI }
     * 
     */
    public XMI createXMI() {
        return new XMI();
    }

    /**
     * Create an instance of {@link Documentation }
     * 
     */
    public Documentation createDocumentation() {
        return new Documentation();
    }

    /**
     * Create an instance of {@link Any }
     * 
     */
    public Any createAny() {
        return new Any();
    }

    /**
     * Create an instance of {@link Difference.Target }
     * 
     */
    public Difference.Target createDifferenceTarget() {
        return new Difference.Target();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Difference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "difference")
    public JAXBElement<Difference> createDifference(Difference value) {
        return new JAXBElement<Difference>(_Difference_QNAME, Difference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Replace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "Replace")
    public JAXBElement<Replace> createReplace(Replace value) {
        return new JAXBElement<Replace>(_Replace_QNAME, Replace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Extension }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "Extension")
    public JAXBElement<Extension> createExtension(Extension value) {
        return new JAXBElement<Extension>(_Extension_QNAME, Extension.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "Add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "Delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "XMI")
    public JAXBElement<XMI> createXMI(XMI value) {
        return new JAXBElement<XMI>(_XMI_QNAME, XMI.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Documentation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/XMI/20131001", name = "documentation")
    public JAXBElement<Documentation> createDocumentation(Documentation value) {
        return new JAXBElement<Documentation>(_Documentation_QNAME, Documentation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "longDescription", scope = Documentation.class)
    public JAXBElement<String> createDocumentationLongDescription(String value) {
        return new JAXBElement<String>(_DocumentationLongDescription_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "owner", scope = Documentation.class)
    public JAXBElement<String> createDocumentationOwner(String value) {
        return new JAXBElement<String>(_DocumentationOwner_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exporter", scope = Documentation.class)
    public JAXBElement<String> createDocumentationExporter(String value) {
        return new JAXBElement<String>(_DocumentationExporter_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exporterVersion", scope = Documentation.class)
    public JAXBElement<String> createDocumentationExporterVersion(String value) {
        return new JAXBElement<String>(_DocumentationExporterVersion_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contact", scope = Documentation.class)
    public JAXBElement<String> createDocumentationContact(String value) {
        return new JAXBElement<String>(_DocumentationContact_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "shortDescription", scope = Documentation.class)
    public JAXBElement<String> createDocumentationShortDescription(String value) {
        return new JAXBElement<String>(_DocumentationShortDescription_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "notice", scope = Documentation.class)
    public JAXBElement<String> createDocumentationNotice(String value) {
        return new JAXBElement<String>(_DocumentationNotice_QNAME, String.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "timestamp", scope = Documentation.class)
    public JAXBElement<XMLGregorianCalendar> createDocumentationTimestamp(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DocumentationTimestamp_QNAME, XMLGregorianCalendar.class, Documentation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Difference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "container", scope = Difference.class)
    public JAXBElement<Difference> createDifferenceContainer(Difference value) {
        return new JAXBElement<Difference>(_DifferenceContainer_QNAME, Difference.class, Difference.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Difference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "difference", scope = Difference.class)
    public JAXBElement<Difference> createDifferenceDifference(Difference value) {
        return new JAXBElement<Difference>(_DifferenceDifference_QNAME, Difference.class, Difference.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Difference.Target }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "target", scope = Difference.class)
    public JAXBElement<Difference.Target> createDifferenceTarget(Difference.Target value) {
        return new JAXBElement<Difference.Target>(_DifferenceTarget_QNAME, Difference.Target.class, Difference.class, value);
    }

}
