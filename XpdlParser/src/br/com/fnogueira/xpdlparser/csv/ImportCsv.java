package br.com.fnogueira.xpdlparser.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;

public class ImportCsv<E> {
	
	protected CellProcessor[] processors;
	
	public ImportCsv() {
		super();
	}

	protected List<Object> readWithCsvListReader(File file) throws 
			IOException {
				return null;
	}


	public CellProcessor[] getProcessors() {
		return processors;
	}

	public void setProcessors(CellProcessor[] processors) {
		this.processors = processors;
	}



}