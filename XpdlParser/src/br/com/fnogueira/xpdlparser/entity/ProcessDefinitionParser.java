package br.com.fnogueira.xpdlparser.entity;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
import org.wfmc._2009.xpdl2.Documentation;
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
import br.com.fnogueira.xpdlparser.plantuml.ActivityDiagramGenerator;
import br.com.fnogueira.xpdlparser.plantuml.ClassDiagramGenerator;
import br.com.fnogueira.xpdlparser.plantuml.UseCaseDiagramGenerator;

public class ProcessDefinitionParser {
	
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
	private static List<FunctionalRequirement> functionalRequirements = new ArrayList<FunctionalRequirement>();
	private static List<NonFunctionalRequirement> nonFunctionalRequirements = new ArrayList<NonFunctionalRequirement>();
	private static List<BusinessRule> businessRules = new ArrayList<BusinessRule>();
	private static List<DomainClass> domainClasses = new ArrayList<DomainClass>();
	private static List<String> useCaseAssociations = new ArrayList<String>();
	private static List<String> textualPostConditions = new ArrayList<String>();
	private static List<PostCondition> postConditions = new ArrayList<PostCondition>();
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	private static File resultsFile = null;
	private static List<PreCondition> preConditions = new ArrayList<PreCondition>();
	
	private static Activity start = new Activity();
	private static List<String> end = new ArrayList<String>();
	
	private static String documentId = "";
	private static String reference = "";
	private static String dateOfCreation = "";
	private static String dateOfModification = "";
	private static String author = "";
	private static String vendor = "";
	private static String xpdlVersion = "";
	private static String processDescription = "";
	private static String processDocumentation = "";
	private static Metadata metadata = new Metadata();
	private static List<Actor> actors = new ArrayList<Actor>();
	private static UseCaseDiagram useCaseDiagram = new UseCaseDiagram();
	private static ClassDiagram classDiagram = new ClassDiagram();
	private static ActivityDiagram activityDiagram = new ActivityDiagram();
	
	private static int numberOfUseCases = 0;
	private static int numberOfClasses = 0;
	private static int numberOfAttributes = 0;
	
	private static Map<String, List<String>> useCaseMap = new HashMap<String, List<String>>();
	private static Map<String, List<String>> classMap = new HashMap<String, List<String>>();
	
	private static UseCaseDiagramGenerator useCaseDiagramGenerator = new UseCaseDiagramGenerator();
	private static ClassDiagramGenerator classDiagramGenerator = new ClassDiagramGenerator();
	private static ActivityDiagramGenerator activityDiagramGenerator = new ActivityDiagramGenerator();
	
	public static String removeTags(String string) {
	    if (string == null || string.length() == 0) {
	        return string;
	    }
	    
	    string = string.replace("&amp;", "").replace("&amp;", "");
	    string = string.replace("quot;", "").replace("quot;", "");
	    string = string.replace("nbsp;", "").replace("nbsp;", "");
	    string = string.replace("#39;", "").replace("#39;", "");
	    
	    Matcher m = REMOVE_TAGS.matcher(string);
	    return m.replaceAll("");
	}
	
	public static void loadElements(File file){
		clear();
		if(rootPackage.getPackageHeader()!=null){
			try {
				Date creation = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rootPackage.getPackageHeader().getCreated().getValue().replace("T", " ").substring(0,16));
				dateOfCreation = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(creation);
				Date modification = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rootPackage.getPackageHeader().getModificationDate().getValue().replace("T", " ").substring(0,16));
				dateOfModification = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(modification);
			} catch (ParseException e) {
				System.out.println("Error getting header...");
			}
			String version = rootPackage.getRedefinableHeader()==null?null:(rootPackage.getRedefinableHeader().getVersion()==null?null:rootPackage.getRedefinableHeader().getVersion().getValue());
			documentId = rootPackage.getName() + version==null?" - version: ":version;
			author = rootPackage.getRedefinableHeader()==null?"":(rootPackage.getRedefinableHeader().getAuthor()==null?"":rootPackage.getRedefinableHeader().getAuthor().getValue());
			reference = file.getName();
			vendor = rootPackage.getPackageHeader().getVendor().getValue();
			xpdlVersion = rootPackage.getPackageHeader().getXPDLVersion().getValue();
			processDescription = removeTags(rootPackage.getPackageHeader().getDescription().getValue());
			processDocumentation = removeTags(rootPackage.getPackageHeader().getDocumentation().getValue());
		}
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
							dictionary.put(activity.getId(), activity.getName()!=null&&!activity.getName().isEmpty()?activity.getName():"");
						}
					}
				}					
				if (o instanceof Activities) {
					Activities activitySet = (Activities) o;
					for (Activity activity : activitySet.getActivity()) {
						activities.add(activity);
						dictionary.put(activity.getId(), activity.getName()!=null&&!activity.getName().isEmpty()?activity.getName():"");
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
					


	public static void printElements(File file, boolean showResults) {
		resultsFile = new File(getHtmlFile(file));
		cleanLog(resultsFile);

		setHeader();
		setActors();
		printActvitiesAndTransitions();		
		extractClasses(file);
		generateUseCaseDiagram(file);
		
		getActivityDiagram(file);
		
		generateXml(file);
		
		if(showResults){
			showResults();
		}

		printSummary(file);
	}

	private static void printSummary(File file) {
		List<String> listString = new ArrayList<String>();
		listString.add(String.valueOf("ACT:"+actors.size()));
		listString.add(String.valueOf("FR:"+functionalRequirements.size()));
		listString.add(String.valueOf("NFR:"+nonFunctionalRequirements.size()));
		listString.add(String.valueOf("BR:"+businessRules.size()));
		listString.add(String.valueOf("UC:"+numberOfUseCases));
		listString.add(String.valueOf("CLS:"+numberOfClasses));
		listString.add(String.valueOf("ATTR:"+numberOfAttributes));
		
		System.out.println(listString);
	}
	
	private static void generateXml(File file){
		RequirementSpecification req = new RequirementSpecification();
		req.setMetadata(metadata);
		req.setActors(actors);
		req.setPreConditions(preConditions);
		req.setPostConditions(postConditions);
		req.setBusinessRules(businessRules);
		req.setFunctionalRequirements(functionalRequirements);
		req.setNonFunctionalRequirements(nonFunctionalRequirements);
		req.setClassDiagram(classDiagram);
		req.setUseCaseDiagram(useCaseDiagram);
		req.setDomainClasses(domainClasses);
		req.setActivityDiagram(activityDiagram);
		
		generateXml(req, file);
	}
	
	public static void generateXml(RequirementSpecification req, File file) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(RequirementSpecification.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		Marshaller marshaller = null;
		try {
			marshaller = context.createMarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		try {
			
			marshaller.marshal(req, new File(getXmlFile(file)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		//gerandoi
		generateHtmlFromXmlAndXstl(req, file);
	}

	private static void generateHtmlFromXmlAndXstl(RequirementSpecification req, File file)
			throws TransformerFactoryConfigurationError {
		// Create Transformer
        TransformerFactory tf = TransformerFactory.newInstance();
        StreamSource xslt = new StreamSource("src/br/com/fnogueira/xpdlparser/entity/Requirements.xslt");
        Transformer transformer = null;
		try {
			transformer = tf.newTransformer(xslt);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
        // Source
        JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(RequirementSpecification.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        JAXBSource source = null;
		try {
			source = new JAXBSource(jc, req);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        // Result
        StreamResult result = new StreamResult(getHtmlFile(file));
        // Transform
        try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}


	private static void showResults() {
		if (Desktop.isDesktopSupported()) {
		    try {
				Desktop.getDesktop().open(resultsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		  System.out.println("Error opening results...");  
		}
	}

	private static void setHeader() {
		metadata.setSource(reference);
		metadata.setXpdlVersion(xpdlVersion);
		metadata.setVendor(vendor);
		metadata.setScenario(documentId);
		metadata.setAuthor(author);
		metadata.setDateOfCreation(dateOfCreation);
		metadata.setDateOfModification(dateOfModification);
		metadata.setDateOfGeneration(String.valueOf(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())));
		metadata.setProcessDescription(processDescription);
		metadata.setProcessDocumentation(processDocumentation);
	}

	private static void setActors() {
		if(!participants.isEmpty()){
			int i =1;
			for (Participant participant : participants) {
				actors.add(new Actor("ACT" + i++, participant.getName(), participant.getDescription().getValue()));
			}
		}
	}

	private static void printActvitiesAndTransitions() {
		for (Activity act : activities) {
			boolean isUseCase = false;
			//log("", false);
			//log(actId++ + "<b> - ACTIVITY: " + ((Activity) act).getName() + "</b>", false);
			for (Object content : ((Activity) act).getContent()) {
				if(content instanceof Route){
					String from = "";
					String to = "";
					for (Transition transition : transitions) {
						if(transition.getFrom().equalsIgnoreCase(((Activity) act).getId())){
							from = dictionary.get(transition.getId());
							break;
						}
					}
					for (Transition transition : transitions) {
						if(transition.getTo().equalsIgnoreCase(((Activity) act).getId())){
							to = dictionary.get(transition.getId());
							break;
						}
					}	
					//log("  - ROUTE: "	+ ((Activity) act).getName() + "( " + from + " --> " + to + " )", false);
					if(isParallel(content)){
						//log("[PARALLEL]", false);
					}
					if(isExclusive(content)){
						//log("[EXCLUSIVE]", false);
					}
					if(isDiverging(content)){
						//log("[DIVERGING]", false);
						BusinessRule br = new BusinessRule(((Activity) act).getId(), ((Activity) act).getName().toString(), null);
						businessRules.add(br);
					}
				}
				else
					if(content instanceof Implementation){
						//log("  [IMPLEMENTATION] ", false);
						if(isImplementation(content)){
							if(isTaskUser(content)){
								//log("[TASKUSER]", false);
								FunctionalRequirement fr = new FunctionalRequirement(((Activity) act).getId(), removeTags(((Activity) act).getName().toString()), null);
								functionalRequirements.add(fr);
								isUseCase = true;
							}
							else if(isTaskService(content)){
								//log("[TASKSERVICE]", false);
								FunctionalRequirement fr = new FunctionalRequirement(((Activity) act).getId(), removeTags(((Activity) act).getName().toString()), null);
								functionalRequirements.add(fr);
								isUseCase = true;
							}
							else if(isTaskScript(content)){
								//log("[TASKSCRIPT]", false);
								FunctionalRequirement fr = new FunctionalRequirement(((Activity) act).getId(), removeTags(((Activity) act).getName().toString()), null);
								functionalRequirements.add(fr);
								isUseCase = true;
							}
						}
					}
					else
						if(content instanceof Event){
							if(((Event) content).getStartEvent()!=null){
								//log("  [START]", false);	
								start = act;
							}
							else	
								if(((Event) content).getEndEvent()!=null){
									//log("  [END]", false);	
									textualPostConditions.add(act.getId());
									end.add(act.getId());
								}
								else
									if(((Event) content).getIntermediateEvent()!=null){
										//log("  [INTERMEDIATE]", false);	
									}
						}
						else
							if(content instanceof Description){
								//log("  - DESCRIPTION: " + ((Description)content).getValue(), false);
							}
							else
								if(/*isUseCase &&*/ content instanceof Performers){
									for (Performer performer : ((Performers) content).getPerformer()) {
										//log("  - PERFORMER: " + dictionary.get(performer.getValue()), false);	
										
										if(useCaseMap.get(dictionary.get(performer.getValue()))==null){
											useCaseMap.put(dictionary.get(performer.getValue()),new ArrayList<String>());
										}
										useCaseMap.get(dictionary.get(performer.getValue())).add(((Activity) act).getName());
										numberOfUseCases++;
									}
								}
								else if(content instanceof ExtendedAttributes){
										for (ExtendedAttribute e : ((ExtendedAttributes) content).getExtendedAttribute()) {
											if(e.getName()!=null && e.getName().equalsIgnoreCase("REGRA")){
												if(e.getValue()!=null && removeTags(e.getValue()).contains(";")){
													String [] arrayValues = removeTags(e.getValue().toString()).split(";");
													if(arrayValues.length>0){
														for (String value : arrayValues) {
															if(value != null && value.trim().length()>1){
																BusinessRule br = new BusinessRule(null, null, value);
																businessRules.add(br);
															}
														}
													}
												}
											}
											else if(e.getName()!=null && e.getName().equalsIgnoreCase("RNF")){
												if(e.getValue()!=null && removeTags(e.getValue()).contains(";")){
													String [] arrayValues = removeTags(e.getValue().toString()).split(";");
													if(arrayValues.length>0){
														for (String value : arrayValues) {
															if(value != null && value.trim().length()>1){
																NonFunctionalRequirement nfr = new NonFunctionalRequirement(null, null, value);
																nonFunctionalRequirements.add(nfr);
															}
														}
													}
												}
											}
										}
									}
			}
		}
		
		//extend e include
		for (FunctionalRequirement fr : functionalRequirements) {
			for (Transition tr : transitions) {
				//se origem igual
				if(tr.getFrom().equalsIgnoreCase(fr.getId())){
					//buscar o destino
					for (FunctionalRequirement frTo : functionalRequirements) {
						if(tr.getTo().equalsIgnoreCase(frTo.getId())){
							String association = dictionary.get(tr.getFrom())+";"+ dictionary.get(tr.getTo());
							useCaseAssociations.add(association);
						}
					}
				}
			}
		}
		
		int frId=1;
		for (Activity activity : activities) {
			for (FunctionalRequirement func : functionalRequirements) {
				if(func.getId().equalsIgnoreCase(activity.getId())){
					for (Object a : (activity.getContent())) {
						if(a instanceof Description){
							func.setDescription(removeTags(((Description)a).getValue().toString()));
							func.setId("FR"+String.valueOf(frId++));
							break;
						}
					}
					break;
				}
			}
			for (BusinessRule bu : businessRules) {
				if(bu.getId()!=null && bu.getId().equalsIgnoreCase(activity.getId())){
					for (Object a : (activity.getContent())) {
						if(a instanceof Description){
							bu.setDescription(removeTags(((Description)a).getValue().toString()));
							break;
						}
					}
					break;
				}
			}
		}
		
		int nfrId=1;
		for (NonFunctionalRequirement nfr : nonFunctionalRequirements) {
			nfr.setId("NFR"+String.valueOf(nfrId++));
		}
		int brId=1;
		for (BusinessRule bu : businessRules) {
			bu.setId("BR"+String.valueOf(brId++));
		}
		
		for (Activity activity : activities) {
			if(activity.getId().equalsIgnoreCase(start.getId())){
				int i=1;
				for (Object obj : activity.getContent()) {
					if(obj instanceof Documentation){
						preConditions.add(new PreCondition("PRE"+i++,removeTags(((Documentation) obj).getValue()),removeTags(((Documentation) obj).getValue())));
					}
				}
			}
		}
		for (String posCondition : textualPostConditions) {
			for (Activity activity : activities) {
				if(activity.getId().equalsIgnoreCase(posCondition)){
					for (Object obj : activity.getContent()) {
						if(obj instanceof Documentation && ((Documentation) obj).getValue().trim().length()>0){
							postConditions.add(new PostCondition("POS"+(postConditions.size()+1),removeTags(((Documentation) obj).getValue()),removeTags(((Documentation) obj).getValue())));
						}
					}
					break;
				}
			}
		}

	}

	private static void extractClasses(File file) {
		for (DataObject dataObject : dataObjects) {
			if(classMap.get(dataObject.getName())==null){
				classMap.put(dataObject.getName(),new ArrayList<String>());
			}
			numberOfClasses++;
			for (Object o : dataObject.getAny()) {
				if(o instanceof ExtendedAttributes){
					for (ExtendedAttribute e : ((ExtendedAttributes) o).getExtendedAttribute()) {
						if(e.getName()!=null && e.getName().equalsIgnoreCase("ATRIBUTOS")){
							if(e.getValue()!=null && removeTags(e.getValue()).contains(";")){
								String [] arrayValues = removeTags(e.getValue().toString()).split(";");
								if(arrayValues.length>0){
									for (String value : arrayValues) {
										if(value != null && value.trim().length()>1){
											String attribute = value.split(":")[0].concat(":").concat(DataTypeEnum.getDataType(value.split(":")[1]));
											classMap.get(dataObject.getName()).add(attribute);	
											numberOfAttributes++;
										}
									}
								}
							}
						}
					}
				}
			}
		}	
		for (DataStoreReference dataStore : dataStoreReferences) {
			if(classMap.get(dictionary.get(dataStore.getDataStoreRef()))==null){
				classMap.put(dictionary.get(dataStore.getDataStoreRef()),new ArrayList<String>());
			}
			numberOfClasses++;
			for (Object o : dataStore.getAny()) {
				if(o instanceof ExtendedAttributes){
					for (ExtendedAttribute e : ((ExtendedAttributes) o).getExtendedAttribute()) {
						if(e.getName()!=null && e.getName().equalsIgnoreCase("ATRIBUTOS")){
							if(e.getValue()!=null && removeTags(e.getValue()).contains(";")){
								String [] arrayValues = removeTags(e.getValue().toString().replace(" ", "")).split(";");
								if(arrayValues.length>0){
									for (String value : arrayValues) {
										if(value!=null && value.trim().length()>1){
											String attribute = value.split(":")[0].concat(":").concat(DataTypeEnum.getDataType(value.split(":")[1]));
											classMap.get(dictionary.get(dataStore.getDataStoreRef())).add(attribute);	
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		for (Actor a : actors) {
			classMap.put(a.getName(), new ArrayList<String>());
		}
		generateClassDiagram(file);
	}

	private static void generateClassDiagram(File file) {
		getDomainClasses();
		String success = classDiagramGenerator.generateClassImage(classMap, file);
		if(success==null || success.contains("Error")){
			System.out.println("System could not generate class diagram from XPDL source.");
		}
		else{
			classDiagram.setImage(classDiagramGenerator.getFile(file, true, false));
		}
	}
	
	private static void getDomainClasses(){
		int id = 1;
		for (String className : classMap.keySet()) {
			DomainClass newClass = new DomainClass("DC"+id++, className.replaceAll(" ", ""), null);
			int aux = 1;
			for (String attribute : classMap.get(className)) {
				String text = attribute.replaceAll("_x0020", "");
				String name = text.split(":")[0].replaceAll(" ", "");
				String dataType = text.split(":")[1];
				newClass.getAttributes().add(new DomainClassAttribute("AT"+aux++, name, dataType, null));
			}
			domainClasses.add(newClass);
		}
	}
	
	private static void getActivityDiagram(File file){
		
		Map<String, List<String>> activitiesMap = new LinkedHashMap<String, List<String>>();		
		
		for (Transition t : transitions) {
			//start
			if(t.getFrom().equals(start.getId())){
				activitiesMap.put("(*)",new ArrayList<String>());
				activitiesMap.get("(*)").add(dictionary.get(t.getTo()));
			}
			//end
			else if(end.contains(t.getTo())){
				if(!activitiesMap.containsKey(dictionary.get(t.getFrom()))){
					activitiesMap.put(dictionary.get(t.getFrom()),new ArrayList<String>());
					activitiesMap.get(dictionary.get(t.getFrom())).add("(*)");
				}
				else{
					activitiesMap.get(dictionary.get(t.getFrom())).add("(*)");
				}
			}
			else{
				if(!activitiesMap.containsKey(dictionary.get(t.getFrom()))){
					activitiesMap.put(dictionary.get(t.getFrom()),new ArrayList<String>());
					activitiesMap.get(dictionary.get(t.getFrom())).add(dictionary.get(t.getTo()));
				}
				else{
					activitiesMap.get(dictionary.get(t.getFrom())).add(dictionary.get(t.getTo()));
				}	
			}
		}

		String success = activityDiagramGenerator.generateActivities(activitiesMap, file);
		if(success==null || success.contains("Error")){
			System.out.println("System could not generate activity diagram from XPDL source.");
		}
		else{
			activityDiagram.setImage(activityDiagramGenerator.getFile(file, true, false));
		}
	}
	
	private static void generateUseCaseDiagram(File file) {
		getUseCases();
		String success = useCaseDiagramGenerator.generateUseCaseImage(useCaseMap, useCaseAssociations, file);
		if(success==null || success.contains("Error")){
			System.out.println("System could not generate use case diagram from XPDL source.");
		}
		else{
			useCaseDiagram.setImage(useCaseDiagramGenerator.getFile(file, true, false));
		}
	}

	private static void getUseCases() {
		for (String actor : useCaseMap.keySet()) {
			int id = 1;
			for (String useCase : useCaseMap.get(actor)) {
				for (Actor a : actors) {
					if(a.getName().equals(actor)){
						a.getUseCases().add(new UseCase("UC"+id++, useCase, null));
					}
				}
			}
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
		ProcessDefinitionParser.rootPackage = rootPackage;
	}
	
	private static boolean isDiverging(Object a){
		return(((Route) a).getGatewayDirection()!=null && ((Route) a).getGatewayDirection().equalsIgnoreCase("Diverging"));
	}
	
	private static boolean isExclusive(Object a){
		return(((Route) a).getExclusiveType()!=null);
	}
	
	private static boolean isParallel(Object a){
		return (((Route) a).getGatewayType().equalsIgnoreCase("Parallel"));
	}
	
	private static boolean isTaskService(Object a) {
		return ((Implementation) a).getTask().getTaskService()!=null;
	}

	private static boolean isTaskUser(Object a) {
		return ((Implementation) a).getTask().getTaskUser()!=null;
	}
	
	private static boolean isTaskScript(Object a) {
		return ((Implementation) a).getTask().getTaskScript()!=null;
	}

	private static boolean isImplementation(Object a) {
		return ((Implementation) a).getTask()!=null;
	}
	
	private static void clear(){
		numberOfUseCases = 0;
		numberOfClasses = 0;
		numberOfAttributes = 0;
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
		actors = new ArrayList<Actor>();
		functionalRequirements = new ArrayList<FunctionalRequirement>();
		nonFunctionalRequirements = new ArrayList<NonFunctionalRequirement>();
		businessRules = new ArrayList<BusinessRule>();
		useCaseAssociations = new ArrayList<String>();
		textualPostConditions = new ArrayList<String>();
		resultsFile = null;
		useCaseMap = new HashMap<String, List<String>>();
		classMap = new HashMap<String, List<String>>();
		classDiagramGenerator = new ClassDiagramGenerator();
		useCaseDiagramGenerator = new UseCaseDiagramGenerator();
		activityDiagramGenerator = new ActivityDiagramGenerator();
		start = null;
		end = new ArrayList<String>();
		documentId = "";
		reference = "";
		dateOfCreation = "";
		dateOfModification = "";
		author = "";
		vendor = "";
		xpdlVersion = "";
		processDescription = "";
		processDocumentation = "";
	}
	
	private static void addElementToStructure(ProcessType wfp, Object object) {
		if(structure.get(wfp.getId())==null){
			structure.put(wfp.getId(), new ArrayList<Object>());
		}
		structure.get(wfp.getId()).add(object);
	}
	
	private static String getHtmlFile(File file) {
		return ParametersEnum.BASE_FOLDER.getValue() + file.getName().replaceFirst("[.][^.]+$", "") + ParametersEnum.HTML_EXTENSION.getValue();
	}
	
	private static String getXmlFile(File file) {
		return ParametersEnum.BASE_FOLDER.getValue() + file.getName().replaceFirst("[.][^.]+$", "") + ParametersEnum.XML_EXTENSION.getValue();
	}
}
