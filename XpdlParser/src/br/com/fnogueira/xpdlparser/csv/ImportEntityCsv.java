package br.com.fnogueira.xpdlparser.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import br.com.fnogueira.xpdlparser.entity.ProcessDefinition;

@SuppressWarnings("rawtypes")
public class ImportEntityCsv extends ImportCsv {
	
	private List<String> fields = new ArrayList<String>();
	private static Map<String, List<String>> classMap = new HashMap<String, List<String>>();
	
	@SuppressWarnings("unchecked")
	public ImportEntityCsv() {
		processors = new CellProcessor[] { 
				new NotNull(), 
				new Optional(),
				new Optional(),
				new Optional()};
	}
	
	public List<String> readWithCsvListReader(File file) throws IOException {
		ICsvListReader listReader = null;
		fields = new ArrayList<String>();
		try {
			ProcessDefinition.log("*ENTITIES (from csv file)", false);
			
			//para importar com ponto e virgula
			listReader = new CsvListReader(new FileReader(file),CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
			
			//quando houver header
			listReader.getHeader(true);
			List<Object> lista;
			String aux = "";
			while ((lista = listReader.read(processors)) != null) {
				lista.get(0).toString().trim();
				lista.get(1).toString().trim();
				lista.get(1).toString().trim();
				lista.get(1).toString().trim();
				
				if(!aux.equalsIgnoreCase(lista.get(0).toString().trim())){
					fields.add(lista.get(0).toString().trim());
					classMap.put(lista.get(0).toString().trim(), new ArrayList<String>());
					ProcessDefinition.log("", false);
					ProcessDefinition.log("["+lista.get(0).toString().trim()+"]", false);	
				}
				ProcessDefinition.log(lista.get(1).toString().trim() + " (" + lista.get(3).toString().trim() + ") : " + lista.get(2).toString().trim(), false);
				classMap.get(lista.get(0).toString().trim()).add(lista.get(3).toString().trim() + " " + lista.get(1).toString().trim());
				aux = lista.get(0).toString().trim();
			}
		}
		catch(SuperCsvException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (listReader != null) {
				listReader.close();
			}
		}
		
		//generating image
		//classDiagramGenerator.generateClassImage(classMap);
		
		return null;
	}

	public List<String> getFields() {
		return fields;
	}
	

}
