package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ClassDiagram")
@XmlAccessorType (XmlAccessType.FIELD)
public class ClassDiagram extends UmlDiagram {
	
	public ClassDiagram(String image) {
		super();
		this.setImage(image);
	}
	public ClassDiagram() {
		super();
	}

}
