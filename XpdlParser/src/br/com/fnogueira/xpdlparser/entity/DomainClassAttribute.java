package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DomainClassAttribute")
@XmlAccessorType (XmlAccessType.FIELD)
public class DomainClassAttribute extends RequirementElement {
	
	private String datatype;
	private String symbol;
	private String defaultValue;
	
	public DomainClassAttribute(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public DomainClassAttribute(String id, String name, String datatype, String symbol) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDatatype(datatype);
		this.setSymbol(symbol);
	}
	public DomainClassAttribute() {
		super();
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

}
