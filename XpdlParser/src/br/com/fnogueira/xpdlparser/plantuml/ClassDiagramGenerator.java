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

public class ClassDiagramGenerator {
	
	public String generateClassImage(Map<String, List<String>> classes, File file) {
		  
		String desc = "";
		OutputStream png = null;
		try {
			png = new FileOutputStream(getFile(file, false, true));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		String source = "@startuml\n";
		for (String className : classes.keySet()) {
			source += "class " + className.replaceAll(" ", "") + " { \n";
			for (String attribute : classes.get(className)) {
				source  += attribute.replaceAll("_x0020", "") + " \n";
			}
			source += " } \n";
		}
		source += "@enduml\n";
		
		//System.out.println(source);
				
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

	public String getFile(File file, boolean html, boolean fullPath) {
		return (fullPath?(html ? ParametersEnum.BASE_FOLDER_HTML.getValue() : ParametersEnum.BASE_FOLDER.getValue()):"").concat(file.getName().replaceFirst("[.][^.]+$", "")).concat(ParametersEnum.CLASS_DIAGRAM_IDENTIFIER.getValue()).concat(ParametersEnum.PNG_EXTENSION.getValue());
	}

}
