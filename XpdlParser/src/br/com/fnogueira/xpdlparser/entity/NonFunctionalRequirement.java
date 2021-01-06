package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NonFunctionalRequirement")
@XmlAccessorType (XmlAccessType.FIELD)
public class NonFunctionalRequirement extends RequirementElement {
	
	public NonFunctionalRequirement(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public NonFunctionalRequirement() {
		super();
	}

}
