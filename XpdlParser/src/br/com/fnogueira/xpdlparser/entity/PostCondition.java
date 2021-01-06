package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PostCondition")
@XmlAccessorType (XmlAccessType.FIELD)
public class PostCondition extends RequirementElement {
	
	public PostCondition(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public PostCondition() {
		super();
	}

}
