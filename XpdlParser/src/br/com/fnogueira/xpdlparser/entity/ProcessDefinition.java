package br.com.fnogueira.xpdlparser.entity;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wfmc._2009.xpdl2.Activities;
import org.wfmc._2009.xpdl2.Activity;
import org.wfmc._2009.xpdl2.ActivitySet;
import org.wfmc._2009.xpdl2.ActivitySets;
import org.wfmc._2009.xpdl2.Artifact;
import org.wfmc._2009.xpdl2.Association;
import org.wfmc._2009.xpdl2.DataAssociation;
import org.wfmc._2009.xpdl2.DataAssociations;
import org.wfmc._2009.xpdl2.DataInputOutput;
import org.wfmc._2009.xpdl2.DataInputOutputs;
import org.wfmc._2009.xpdl2.DataObject;
import org.wfmc._2009.xpdl2.DataObjects;
import org.wfmc._2009.xpdl2.DataStore;
import org.wfmc._2009.xpdl2.DataStoreReference;
import org.wfmc._2009.xpdl2.DataStoreReferences;
import org.wfmc._2009.xpdl2.Description;
import org.wfmc._2009.xpdl2.Event;
import org.wfmc._2009.xpdl2.ExtendedAttribute;
import org.wfmc._2009.xpdl2.ExtendedAttributes;
import org.wfmc._2009.xpdl2.Implementation;
import org.wfmc._2009.xpdl2.Lane;
import org.wfmc._2009.xpdl2.PackageType;
import org.wfmc._2009.xpdl2.Participant;
import org.wfmc._2009.xpdl2.Performer;
import org.wfmc._2009.xpdl2.Performers;
import org.wfmc._2009.xpdl2.Pool;
import org.wfmc._2009.xpdl2.ProcessType;
import org.wfmc._2009.xpdl2.Route;
import org.wfmc._2009.xpdl2.Transition;
import org.wfmc._2009.xpdl2.Transitions;

import br.com.fnogueira.xpdlparser.enums.DataTypeEnum;
import br.com.fnogueira.xpdlparser.enums.ParametersEnum;
import br.com.fnogueira.xpdlparser.plantuml.ClassDiagramGenerator;
import br.com.fnogueira.xpdlparser.plantuml.UseCaseDiagramGenerator;

public class ProcessDefinition {
	
	private static final String HTML_BR = "</br>";
	private static final String HTML_HEADER = "<html><font style=font-size:10pt;'><head><meta charset='UTF-8'></head><body>";
	private final static String HTML_FOOTER = "</body></font></html>";
	private static final String HTML_HR = "<hr>";
	private static PackageType rootPackage = new PackageType();
	private static List<Activity> activities = new ArrayList<Activity>();
	private static List<Pool> pools = new ArrayList<Pool>();
	private static List<Lane> lanes = new ArrayList<Lane>();
	private static List<Transition> transitions = new ArrayList<Transition>();
	private static Map<String, String> dictionary = new HashMap<String, String>();
	private static Map<String, List<Object>> structure = new HashMap<String, List<Object>>();
	private static List<Association> associations = new ArrayList<Association>();
	private static List<Artifact> artifacts = new ArrayList<Artifact>();
	private static List<DataAssociation> dataAssociations = new ArrayList<DataAssociation>();
	private static List<DataObject> dataObjects = new ArrayList<DataObject>();
	private static List<DataStoreReference> dataStoreReferences = new ArrayList<DataStoreReference>();
	private static List<DataInputOutput> dataInputOutputs = new ArrayList<DataInputOutput>();
	private static List<DataStore> dataStores = new ArrayList<DataStore>();
	private static List<ProcessType> processTypes = new ArrayList<ProcessType>();
	private static List<Participant> participants = new ArrayList<Participant>();
	private static Map<String, List<String>> ordered = new HashMap<String, List<String>>();
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	private static File resultsFile = null;
	
	private static Map<String, List<String>> useCaseMap = new HashMap<String, List<String>>();
	private static Map<String, List<String>> classMap = new HashMap<String, List<String>>();
	
	private static UseCaseDiagramGenerator useCaseDiagramGenerator = new UseCaseDiagramGenerator();
	private static ClassDiagramGenerator classDiagramGenerator = new ClassDiagramGenerator();
	
	public static String removeTags(String string) {
	    if (string == null || string.length() == 0) {
	        return string;
	    }

	    Matcher m = REMOVE_TAGS.matcher(string);
	    return m.replaceAll("");
	}
	
	public static void loadElements(){
		clear();
		if(rootPackage.getParticipants()!=null){
			for (Participant p : rootPackage.getParticipants().getParticipant()) {
				participants.add(p);
				dictionary.put(p.getId(), p.getName()!=null&&!p.getName().isEmpty()?p.getName():"<participant>");
			}			
		}
		for (Pool pool : rootPackage.getPools().getPool()) {
				pools.add(pool);
				dictionary.put(pool.getId(), pool.getName()!=null&&!pool.getName().isEmpty()?pool.getName():"<pool>");
				for (Lane lane : pool.getLanes().getLane()) {
					lanes.add(lane);
					dictionary.put(lane.getId(), lane.getName()!=null&&!lane.getName().isEmpty()?lane.getName():"<lane>");
				}
			}
		for (Object o : rootPackage.getAssociations().getAssociationAndAny()) {
			if (o instanceof Association) {
				Association association = (Association) o;
				associations.add(association);
				dictionary.put(association.getId(), association.getName()!=null&&!association.getName().isEmpty()?association.getName():"<association>");
			}
		}	
		if(rootPackage.getDataStores()!=null){
			for (Object o : rootPackage.getDataStores().getDataStore()) {
				if(o instanceof DataStore){
					DataStore dataStore = (DataStore) o;
					dataStores.add(dataStore);
					dictionary.put(dataStore.getId(), dataStore.getName()!=null&&!dataStore.getName().isEmpty()?dataStore.getName():"<dataStore>");
				}				
			}			
		}
		if(rootPackage.getArtifacts()!=null){
			for (Object o : rootPackage.getArtifacts().getArtifactAndAny()) {
				if(o instanceof Artifact){
					Artifact artifact = (Artifact) o;
					artifacts.add(artifact);
					dictionary.put(artifact.getId(), artifact.getName()!=null&&!artifact.getName().isEmpty()?artifact.getName():"<artifact>");
				}				
			}			
		}
		for (ProcessType wfp : rootPackage.getWorkflowProcesses().getWorkflowProcess()) {
			processTypes.add(wfp);
			dictionary.put(wfp.getId(), wfp.getName()!=null&&!wfp.getName().isEmpty()?wfp.getName():"<workflow>");
			for (Object o : wfp.getContent()) {
				if (o instanceof ActivitySets) {
					ActivitySets activitySets = (ActivitySets) o;
					for (ActivitySet activitySet : activitySets.getActivitySet()) {
						for (Activity activity : activitySet.getActivities().getActivity()) {
							activities.add(activity);
							dictionary.put(activity.getId(), activity.getName()!=null&&!activity.getName().isEmpty()?activity.getName():"<activity>");
						}
					}
				}					
				if (o instanceof Activities) {
					Activities activitySet = (Activities) o;
					for (Activity activity : activitySet.getActivity()) {
						activities.add(activity);
						dictionary.put(activity.getId(), activity.getName()!=null&&!activity.getName().isEmpty()?activity.getName():"<activity>");
						addElementToStructure(wfp, activity);
					}
				}				
				if (o instanceof Transitions) {
					Transitions transitionSet = (Transitions) o;
					for (Transition transition : transitionSet.getTransition()) {
						transitions.add(transition);
						dictionary.put(transition.getId(), transition.getName()!=null&&!transition.getName().isEmpty()?transition.getName():"<transition>");
					}
				}
				if (o instanceof DataAssociations) {
					DataAssociations dataAssociationSet = (DataAssociations) o;
					for (DataAssociation dataAssociation : dataAssociationSet.getDataAssociation()) {
						dataAssociations.add(dataAssociation);
						dictionary.put(dataAssociation.getId(), dataAssociation.getName()!=null&&!dataAssociation.getName().isEmpty()?dataAssociation.getName():"<dataAssociation>");
					}
				}
				if (o instanceof DataObjects) {
					DataObjects dataObjectSet = (DataObjects) o;
					for (DataObject dataObject : dataObjectSet.getDataObject()) {
						dataObjects.add(dataObject);
						dictionary.put(dataObject.getId(), dataObject.getName()!=null&&!dataObject.getName().isEmpty()?dataObject.getName():"<dataObject>");
					}
				}
				if (o instanceof DataStoreReferences) {
					DataStoreReferences dataStoreReferenceSet = (DataStoreReferences) o;
					for (DataStoreReference dataStoreReference : dataStoreReferenceSet.getDataStoreReference()) {
						dataStoreReferences.add(dataStoreReference);
						dictionary.put(dataStoreReference.getId(), dataStoreReference.getName()!=null&&!dataStoreReference.getName().isEmpty()?dataStoreReference.getName():"<dataStoreReference>");
					}
				}
				if(o instanceof DataInputOutputs){
					DataInputOutputs dataInputOutputSet = (DataInputOutputs) o;
					for (DataInputOutput dataInputOutput : dataInputOutputSet.getDataInput()) {
						dataInputOutputs.add(dataInputOutput);
						dictionary.put(dataInputOutput.getId(), dataInputOutput.getName()!=null&&!dataInputOutput.getName().isEmpty()?dataInputOutput.getName():"<dataInput>");
					}
					for (DataInputOutput dataInputOutput : dataInputOutputSet.getDataOutput()) {
						dataInputOutputs.add(dataInputOutput);
						dictionary.put(dataInputOutput.getId(), dataInputOutput.getName()!=null&&!dataInputOutput.getName().isEmpty()?dataInputOutput.getName():"<dataOutput>");
					}					
				}
			}
		}
	}
	
					
	private static void addElementToStructure(ProcessType wfp, Object object) {
		if(structure.get(wfp.getId())==null){
			structure.put(wfp.getId(), new ArrayList<Object>());
		}
		structure.get(wfp.getId()).add(object);
	}


	public static void printElements(File file) {
		
		//teste de conversão de excel para csv
		/*File inputFile2 = new File("E:\\teste\\sentencas.xlsx");
		File inputFile1 = new File("E:\\teste\\input1.xls");
		File outputFile1 = new File("E:\\teste\\output1.csv");
		File outputFile2 = new File("E:\\teste\\output2.csv");
		ExcelToCSV.convertToXls(inputFile1, outputFile1);
		ExcelToCSV.convertToXlsx(inputFile2, outputFile2);*/
		
		resultsFile = new File(getFile(file));
		cleanLog(resultsFile);
		log(HTML_HEADER, true);
		log("*DIAGRAM: " + rootPackage.getName(), false);
		log(HTML_HR, true);
		log("*PARTICIPANTS", false);
		for (Participant participant : participants) {
			log(" - "	+ participant.getName(), false);
		}
		log(HTML_HR, true);
		log("*POOLS", false);
		int poolId = 1;
		for (Pool pool : pools) {
			log(poolId++ + "<b> - POOL: "	+ pool.getName() + "</b>", false);
			for (ProcessType wfp : processTypes) {
				if(pool.getProcess().equals(wfp.getId())){
					log(" - WORKFLOW PROCESS: "	+ wfp.getName(), false);
					//getElementsFromPoolOrLane(wfp);	
				}
			}
			log(HTML_HR, true);
			log("*LANES", false);
			int laneId = 1;
			for (Lane lane : pool.getLanes().getLane()) {
				log(laneId++ + "<b> - LANE: "	+ lane.getName() + "</b>", false);
				for (ProcessType wfp : processTypes) {
					if(pool.getProcess().equals(wfp.getId())){
						//log(" - WORKFLOW PROCESS: "	+ wfp.getName());
						//getElementsFromPoolOrLane(wfp);	
					}
				}
			}	
			log(HTML_HR, true);
		}
		
		for (ProcessType wfp : processTypes) {
			getElementsFromPoolOrLane(wfp);
		}
		
		printAditionalElements(file);

		//printOrderedList();
		
		useCaseDiagramGenerator.generateUseCaseImage(useCaseMap, null, file);
		log("<h3>Use Case Diagram</h3>",true);
		log("<img src='file://" + useCaseDiagramGenerator.getFile(file, true, true) +"'/>",true);
		
		/*List<String> images = new ArrayList<String>();
		File useCaseFile = new File("E:\\TESTE\\ClassDiagram.png");
		images.add(useCaseFile.getAbsolutePath());
		File classFile = new File("E:\\TESTE\\UseCaseDiagram.png");
		images.add(classFile.getAbsolutePath());
		imageHandler.handleImages(images);
		imageHandler.handleImagesSideBySide(images);*/
		
		log(HTML_HR, true);
		
		//compareXpdlAndCsv();
		
		log(HTML_FOOTER,true);
		
		if (Desktop.isDesktopSupported()) {
		    try {
				Desktop.getDesktop().open(resultsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		  System.out.println("Error retrieving results...");  
		}

	}

	private static String getFile(File file) {
		return ParametersEnum.BASE_FOLDER.getValue() + file.getName().replaceFirst("[.][^.]+$", "") + ParametersEnum.HTML_EXTENSION.getValue();
	}

	/*private static void compareXpdlAndCsv() {
		fileCsv = new File(resultsFile.getParent() + "\\" + "Dados.csv");
		try {
			importEntityCsv.readWithCsvListReader(fileCsv);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		log(HTML_BR, true);
				
		//searching for entity in XPDL dataStores
		for (String entity : importEntityCsv.getFields()) {
			boolean dsFound = false;
			for (DataStore ds : dataStores) {
				if(entity.equalsIgnoreCase(ds.getName())){
					dsFound = true;
					break;
				}
			}
			if(!dsFound){
				log("*Entity " + entity + " not found in XPDL file.",false);
			}
		}
		//searching for dataStore in csv file
		for (DataStore ds : dataStores) {
			if(ds.getName().trim().length()>0){
				boolean entityFound = false;
				for (String entity : importEntityCsv.getFields()) {
					if(ds.getName().equalsIgnoreCase(entity)){
						entityFound = true;
						break;
					}
				}
				if(!entityFound){
					log("*DataStore " + ds.getName() + " not found in csv file.",false);
				}				
			}
		}
	}*/

	private static void getElementsFromPoolOrLane(ProcessType wfp) {
		if(structure.get(wfp.getId())!=null){
			int actId = 1;
			for (Object o : structure.get(wfp.getId())) {
				if (o instanceof Activity) {
					log("", false);
					log(actId++ + "<b> - ACTIVITY: " + ((Activity) o).getName() + "</b>", false);
					for (Object a : ((Activity) o).getContent()) {
						if(a instanceof Route){
							String from = "";
							String to = "";
							for (Transition transition : transitions) {
								if(transition.getFrom().equalsIgnoreCase(((Activity) o).getId())){
									from = dictionary.get(transition.getId());
									break;
								}
							}
							for (Transition transition : transitions) {
								if(transition.getTo().equalsIgnoreCase(((Activity) o).getId())){
									to = dictionary.get(transition.getId());
									break;
								}
							}	
							log("  - ROUTE: "	+ ((Activity) o).getName() + "( " + from + " --> " + to + " )", false);
							if(((Route) a).getGatewayType().equalsIgnoreCase("Parallel")){
								log("[PARALLEL]", false);
							}
							if(((Route) a).getExclusiveType()!=null){
								log("[EXCLUSIVE]", false);
							}
							if(((Route) a).getGatewayDirection()!=null && ((Route) a).getGatewayDirection().equalsIgnoreCase("Diverging")){
								log("[DIVERGING]", false);
							}
						}
						else
						if(a instanceof Implementation){
							log("  [IMPLEMENTATION] ", false);
							if(((Implementation) a).getTask()!=null){
								if(((Implementation) a).getTask().getTaskUser()!=null){
									log("[TASKUSER]", false);
								}
								else if(((Implementation) a).getTask().getTaskService()!=null){
										log("[TASKSERVICE]", false);
								}
							}
						}
						if(a instanceof Event){
							if(((Event) a).getStartEvent()!=null){
								log("  [START]", false);	
							}
							else	
								if(((Event) a).getEndEvent()!=null){
									log("  [END]", false);	
								}
								else
									if(((Event) a).getIntermediateEvent()!=null){
										log("  [INTERMEDIATE]", false);	
									}
						}				
						if(a instanceof Description){
							log("  - DESCRIPTION: " + ((Description)a).getValue(), false);
						}
						if(a instanceof Performers){
							for (Performer performer : ((Performers) a).getPerformer()) {
								log("  - PERFORMER: " + dictionary.get(performer.getValue()), false);	
								
								if(useCaseMap.get(dictionary.get(performer.getValue()))==null){
									useCaseMap.put(dictionary.get(performer.getValue()),new ArrayList<String>());
								}
								useCaseMap.get(dictionary.get(performer.getValue())).add(((Activity) o).getName());
							}
						}
					}
				}
			}
		}
	}
	
	public static void printOrderedList(){
		//primeira interação
		String start = "";
		for (Activity o : activities) {
			for (Object a : ((Activity) o).getContent()) {
				if(a instanceof Event){
					//primeiro da lista
					if(((Event) a).getStartEvent()!=null){
						start = o.getId();
						ordered.put(o.getId(), new ArrayList<String>());
						break;
					}
					
				}
			}
			break;
		}
		findNext(start, start);
	}
	
	//monta uma lista com as transições a partir de um início
	private static void findNext(String id, String start){
		for (Transition t : transitions) {
			if(id.equals(t.getFrom().toString())){
				ordered.get(start).add(t.getId());
				ordered.get(start).add(t.getTo());
				
				findNext(t.getTo(), start);
			}
		}
		//System.out.println(ordered.get(start));
	}


	private static void printAditionalElements(File file) {
		log(HTML_HR, true);
		log("*TRANSITIONS", false);
		int trsId = 1;
		for (Transition transition : transitions) {
			if(transition.getCondition()!=null && transition.getCondition().getType() !=null && transition.getCondition().getType().equalsIgnoreCase("CONDITION")){
				log(trsId++ + "<b> - CONDITION: " + transition.getName() + " ("+ dictionary.get(transition.getFrom())+" --> "+dictionary.get(transition.getTo())+") </b>", false);
			}
			else{
				log(trsId++ + "<b> - TRANSITION: "	+ transition.getName() + " ("+ dictionary.get(transition.getFrom())+" --> "+dictionary.get(transition.getTo())+") </b>", false);
			}
			log("  - DESCRIPTION: " + transition.getDescription().getValue(), false);
		}	
		log(HTML_HR, true);
		log("*ASSOCIATIONS", false);
		for (Association association : associations) {
			log(" - "	+ association.getName() + " ("+ dictionary.get(association.getSource())+" --> "+dictionary.get(association.getTarget())+")", false);
		}
		log(HTML_HR, true);
		log("*DATA_ASSOCIATIONS", false);
		for (DataAssociation dataAssociation : dataAssociations) {
			log(" - "	+ dataAssociation.getName() + " ("+ dictionary.get(dataAssociation.getFrom())+" --> "+dictionary.get(dataAssociation.getTo())+")", false);
		}	
		log(HTML_HR, true);
		log("*ARTIFACTS", false);
		for (Artifact artifact : artifacts) {
			log(" - "	+ artifact.getName() + " [" + artifact.getArtifactType() + "] - " + artifact.getTextAnnotation(), false);
		}
		log(HTML_HR, true);
		log("*DATA_OBJECT", false);
		int doId = 1;
		for (DataObject dataObject : dataObjects) {
			log(doId++ + " - "	+ dataObject.getName(), false);
			log("- " + dataObject.getObject().getDocumentation().getValue(), false);	
			
			for (Object o : dataObject.getAny()) {
				if(o instanceof ExtendedAttributes){
					for (ExtendedAttribute e : ((ExtendedAttributes) o).getExtendedAttribute()) {
						log(" - " + e.getName() + ": " + e.getValue(), false);
						
						//no atributo extendido, buscar por cada campo informado
						String [] arrayValues = removeTags(removeTags(e.getValue().toString())).split(";");
						for (String value : arrayValues) {
							if(value != null && value.trim().length()>1){
								if(classMap.get(dataObject.getName())==null){
									classMap.put(dataObject.getName(),new ArrayList<String>());
								}
								//inserindo o atriuto com seu data type correto
								String attribute = value.split(":")[0].concat(":").concat(DataTypeEnum.getDataType(value.split(":")[1]));
								classMap.get(dataObject.getName()).add(attribute);	
							}
						}
					}
				}
			}
		}		
		log(HTML_HR, true);
		log("*DATA_INPUT_OUTPUT", false);
		for (DataInputOutput dataInputOutput : dataInputOutputs) {
			log(" - "	+ dataInputOutput.getName(), false);
		}
		log(HTML_HR, true);
		log("*DATA_STORE", false);
		int dsId = 1;
		for (DataStore dataStore : dataStores) {
			log(dsId++ + " - "	+ dataStore.getName(), false);
			/*String [] arrayValues = removeTags(dataStore.getObject().getDocumentation().getValue()).split(";");
			for (String value : arrayValues) {
				log("- Field: " + value, false);	
			}
			dataStore.getOtherAttributes();*/
		}
		log(HTML_HR, true);
		log("*DATA_STORE_REFERENCE", false);
		int dsrId = 1;
		for (DataStoreReference dataStore : dataStoreReferences) {
			log(dsrId++ + " - "	+ dictionary.get(dataStore.getDataStoreRef()), false);
			for (Object o : dataStore.getAny()) {
				if(o instanceof ExtendedAttributes){
					for (ExtendedAttribute e : ((ExtendedAttributes) o).getExtendedAttribute()) {
						log(" - " + e.getName() + ": " + e.getValue(), false);
						
						//no atributo extendido, buscar por cada campo informado
						String [] arrayValues = removeTags(removeTags(e.getValue().toString())).split(";");
						for (String value : arrayValues) {
							if(value!=null && value.trim().length()>1){
								if(classMap.get(dictionary.get(dataStore.getDataStoreRef()))==null){
									classMap.put(dictionary.get(dataStore.getDataStoreRef()),new ArrayList<String>());
								}
								//inserindo o atriuto com seu data type correto
								String attribute = value.split(":")[0].concat(":").concat(DataTypeEnum.getDataType(value.split(":")[1]));
								classMap.get(dictionary.get(dataStore.getDataStoreRef())).add(attribute);	
							}
						}
					}
				}
			}
		}
		System.out.println(classMap);		
		classDiagramGenerator.generateClassImage(classMap, file);
		log("<h3>Class Diagram</h3>",true);
		log("<img src='" + classDiagramGenerator.getFile(file, true, false) +"'/>",true);
	}
	
	public static void log(String text, boolean htmlAllowed){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(resultsFile.getAbsolutePath().toString(), true)))) {
			if(!htmlAllowed){
				text = removeTags(text);
			}
		    out.println(text+HTML_BR);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public static void cleanLog(File file) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		finally{
			pw.close();	
		}
	}

	public static PackageType getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(PackageType rootPackage) {
		ProcessDefinition.rootPackage = rootPackage;
	}
	
	private static void clear(){
		activities = new ArrayList<Activity>();
		pools = new ArrayList<Pool>();
		lanes = new ArrayList<Lane>();
		transitions = new ArrayList<Transition>();
		dictionary = new HashMap<String, String>();
		structure = new HashMap<String, List<Object>>();
		associations = new ArrayList<Association>();
		artifacts = new ArrayList<Artifact>();
		dataAssociations = new ArrayList<DataAssociation>();
		dataObjects = new ArrayList<DataObject>();
		dataInputOutputs = new ArrayList<DataInputOutput>();
		dataStores = new ArrayList<DataStore>();
		dataStoreReferences = new ArrayList<DataStoreReference>();
		processTypes = new ArrayList<ProcessType>();
		participants = new ArrayList<Participant>();
		resultsFile = null;
		ordered = new HashMap<String, List<String>>();
		useCaseMap = new HashMap<String, List<String>>();
		classMap = new HashMap<String, List<String>>();
		classDiagramGenerator = new ClassDiagramGenerator();
		useCaseDiagramGenerator = new UseCaseDiagramGenerator();
	}
	
}
