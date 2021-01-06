package br.com.fnogueira.xpdlparser.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Actor")
@XmlAccessorType (XmlAccessType.FIELD)
public class Actor extends RequirementElement {
	
	@XmlElementWrapper(name = "useCases")
	@XmlElement(name = "UseCase")
	private List<UseCase> useCases = new ArrayList<UseCase>();
	
	public Actor(String id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public Actor() {
		super();
	}
	public List<UseCase> getUseCases() {
		return useCases;
	}
	public void setUseCases(List<UseCase> useCases) {
		this.useCases = useCases;
	}

}
