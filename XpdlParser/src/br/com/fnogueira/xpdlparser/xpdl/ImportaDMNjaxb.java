package br.com.fnogueira.xpdlparser.xpdl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.fnogueira.xpdlparser.entity.dmn.Definitions;
import br.com.fnogueira.xpdlparser.entity.dmn.ObjectFactory;

public class ImportaDMNjaxb {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		try {

			File file = new File("E:\\workspace\\ImportaXML\\src\\br\\com\\fernando\\importaxml\\dmn\\table.dmn");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Definitions.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement rootElement = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
			Definitions root = (Definitions) rootElement.getValue();
			
			root.getId();
			
			ObjectFactory oj = new ObjectFactory();
			oj.createDefinitions();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
