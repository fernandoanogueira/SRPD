package br.com.fnogueira.xpdlparser.xpdl;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.wfmc._2002.xpdl1.Package;
import org.wfmc._2009.xpdl2.PackageType;

import br.com.fnogueira.xpdlparser.entity.ProcessDefinition;

public class ImportaXMLjaxb {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {
		try {

			File file = new File("C:\\Users\\Fernando\\Desktop\\exemplo_wikipedia_bpmn_dmn_xmi.xpdl");
			JAXBContext jaxbContext = JAXBContext.newInstance(Package.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement rootElement = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
			Package root = (Package) rootElement.getValue();
			
			ProcessDefinition processDefinition = new ProcessDefinition();
			//processDefinition.setRootPackage(root);
			ProcessDefinition.loadElements();
		//	ProcessDefinition.printElements();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
