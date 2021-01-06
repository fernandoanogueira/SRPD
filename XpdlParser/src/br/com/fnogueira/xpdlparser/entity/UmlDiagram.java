package br.com.fnogueira.xpdlparser.entity;

public class UmlDiagram {
	private String image;
	
	public UmlDiagram(String image) {
		super();
		this.image = image;
	}
	public UmlDiagram() {
		super();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
