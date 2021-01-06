package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Metadata")
@XmlAccessorType (XmlAccessType.FIELD)
public class Metadata {
	
	private String source;
	private String xpdlVersion;
	private String vendor;
	private String author;	
	private String dateOfCreation;
	private String dateOfModification;	
	private String dateOfGeneration;
	private String scenario;
	private String processDescription;
	private String processDocumentation;
	
	public Metadata(String source, String xpdlVersion, String vendor,
			String author, String dateOfCreation, String dateOfModification,
			String dateOfGeneration, String scenario,
			String processDescription, String processDocumentation) {
		super();
		this.source = source;
		this.xpdlVersion = xpdlVersion;
		this.vendor = vendor;
		this.author = author;
		this.dateOfCreation = dateOfCreation;
		this.dateOfModification = dateOfModification;
		this.dateOfGeneration = dateOfGeneration;
		this.scenario = scenario;
		this.processDescription = processDescription;
		this.processDocumentation = processDocumentation;
	}
	
	public Metadata() {
		super();
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getXpdlVersion() {
		return xpdlVersion;
	}
	public void setXpdlVersion(String xpdlVersion) {
		this.xpdlVersion = xpdlVersion;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public String getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(String dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
	public String getDateOfGeneration() {
		return dateOfGeneration;
	}
	public void setDateOfGeneration(String dateOfGeneration) {
		this.dateOfGeneration = dateOfGeneration;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getProcessDescription() {
		return processDescription;
	}
	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}
	public String getProcessDocumentation() {
		return processDocumentation;
	}
	public void setProcessDocumentation(String processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

}
