package br.com.fnogueira.xpdlparser.enums;

public enum ParametersEnum {
	
	BASE_FOLDER("E:\\teste\\"),
	BASE_FOLDER_HTML("E:/teste/"),
	CLASS_DIAGRAM_IDENTIFIER("_cd"),
	USE_CASE_DIAGRAM_IDENTIFIER("_ucd"),
	ACT_DIAGRAM_IDENTIFIER("_actd"),
	CSV_EXTENSION(".csv"),
	JPEG_EXTENSION(".jpeg"),
	PNG_EXTENSION(".png"),
	XML_EXTENSION(".xml"),
	HTML_EXTENSION(".htm"),
	ACTIVITY_EVENT("(*)");
	
	private String value;
	
	private ParametersEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	


}
