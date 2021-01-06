package br.com.fnogueira.xpdlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ActivityDiagram")
@XmlAccessorType (XmlAccessType.FIELD)
public class ActivityDiagram extends UmlDiagram {
	
	public ActivityDiagram(String image) {
		super();
		this.setImage(image);
	}
	public ActivityDiagram() {
		super();
	}

}
