package br.com.fnogueira.xpdlparser.xpdl;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.wfmc._2009.xpdl2.ObjectFactory;
import org.wfmc._2009.xpdl2.PackageType;

import br.com.fnogueira.xpdlparser.entity.ProcessDefinition;

public class ImportaXPDLjaxb {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {
		try {

			File file = new File("E:\\workspace\\ImportaXML\\src\\br\\com\\fernando\\importaxml\\xpdl\\DigitalizacaodeDocumentos.xpdl");
			

			JAXBContext jaxbContext = JAXBContext.newInstance(PackageType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement rootElement = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
			PackageType root = (PackageType) rootElement.getValue();
			
			ObjectFactory oj = new ObjectFactory();
			oj.createPackage(root);
			
			ProcessDefinition processDefinition = new ProcessDefinition();
			processDefinition.setRootPackage(root);
			ProcessDefinition.loadElements();
			ProcessDefinition.printElements(null);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
