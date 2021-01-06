package br.com.fnogueira.xpdlparser.plantuml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.SourceStringReader;
import br.com.fnogueira.xpdlparser.enums.ParametersEnum;

public class ActivityDiagramGenerator {
	
	public String generateActivities(Map<String, List<String>> activitiesMap, File file) {
		  
		String desc = "";
		OutputStream png = null;
		try {
			png = new FileOutputStream(getFile(file, false, true));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		String source = "@startuml\n";
		for (String from : activitiesMap.keySet()) {
			//continuous
			if(activitiesMap.get(from).size()==1){
				source += getQuoted(from) + " --> " + getQuoted(activitiesMap.get(from).get(0)) + "\n";
			}
			//decision
			else{
				source += "if " + getQuoted(from) + " then \n";
				source += " --> " + getQuoted(activitiesMap.get(from).get(0)) + "\n";
				source += "else \n";
				source += " --> " + getQuoted(activitiesMap.get(from).get(1)) + "\n";
				source += "endif \n";
			}
		}
		source += "@enduml";
		System.out.println(source);
		
		SourceStringReader reader = new SourceStringReader(source);
		// Write the first image to "png"
		try {
			desc = reader.generateImage(png);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Return a null string if no generation
		
		try {
			reader.generateImage(png);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			png.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return desc;
	}
	
	private String getQuoted(String text){
		if(!text.equals("(*)")){
			return '"' + text + '"';
		}
		else{
			return text;
		}
			
	}

	public String getFile(File file, boolean html, boolean fullPath) {
		return (fullPath?(html ? ParametersEnum.BASE_FOLDER_HTML.getValue() : ParametersEnum.BASE_FOLDER.getValue()):"").concat(file.getName().replaceFirst("[.][^.]+$", "")).concat(ParametersEnum.ACT_DIAGRAM_IDENTIFIER.getValue()).concat(ParametersEnum.PNG_EXTENSION.getValue());
	}

}
