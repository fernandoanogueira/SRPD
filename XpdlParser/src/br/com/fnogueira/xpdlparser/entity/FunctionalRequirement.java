package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FunctionalRequirement")
@XmlAccessorType (XmlAccessType.FIELD)
public class FunctionalRequirement extends RequirementElement {

	public FunctionalRequirement(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public FunctionalRequirement() {
		super();
	}

}
