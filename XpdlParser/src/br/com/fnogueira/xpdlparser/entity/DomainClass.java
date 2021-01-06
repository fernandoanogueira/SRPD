package br.com.fnogueira.xpdlparser.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DomainClass")
@XmlAccessorType (XmlAccessType.FIELD)
public class DomainClass extends RequirementElement {
	
	@XmlElementWrapper(name = "attributes")
	@XmlElement(name = "Attribute")
	private List<DomainClassAttribute> attributes = new ArrayList<DomainClassAttribute>();
	
	public DomainClass(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public DomainClass() {
		super();
	}
	public List<DomainClassAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<DomainClassAttribute> attributes) {
		this.attributes = attributes;
	}

}
