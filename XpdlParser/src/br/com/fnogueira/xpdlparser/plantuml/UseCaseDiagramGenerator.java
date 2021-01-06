package br.com.fnogueira.xpdlparser.plantuml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import br.com.fnogueira.xpdlparser.enums.ParametersEnum;
import net.sourceforge.plantuml.SourceStringReader;

public class UseCaseDiagramGenerator {
	
	public String generateUseCaseImage(Map<String, List<String>> useCases, List<String> associations, File file) {
		  
		String desc = "";
		OutputStream png = null;
		try {
			png = new FileOutputStream(getFile(file, false, true));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String source = "@startuml\n";
		source += "left to right direction \n";
		for (String actor : useCases.keySet()) {
			for (String useCase : useCases.get(actor)) {
				//excluindo o que já estiver como include
				boolean alreadyIncluded = false;
				/*for (String aux : associations) {
					if(aux.split(";")[1].equalsIgnoreCase(useCase)){
						alreadyIncluded = true;
						break;
					}
				}*/
				if(!alreadyIncluded){
					source += actor.replace(" ", "")  + "-> (" + useCase + ") \n";	
				}
			}
		}
		
		//TODO - comentado por enquanto
		/*for (String aux : associations) {
			source+= "(" +aux.split(";")[0]+") .> (" + aux.split(";")[1] + ") :include \n";
		}*/
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
		return (fullPath?(html ? ParametersEnum.BASE_FOLDER_HTML.getValue() : ParametersEnum.BASE_FOLDER.getValue()):"").concat(file.getName().replaceFirst("[.][^.]+$", "")).concat(ParametersEnum.USE_CASE_DIAGRAM_IDENTIFIER.getValue()).concat(ParametersEnum.PNG_EXTENSION.getValue());
	}
}
