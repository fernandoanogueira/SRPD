package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UseCaseDiagram")
@XmlAccessorType (XmlAccessType.FIELD)
public class UseCaseDiagram extends UmlDiagram {
	
	public UseCaseDiagram(String image) {
		super();
		this.setImage(image);
	}
	public UseCaseDiagram() {
		super();
	}

}