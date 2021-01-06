package br.com.fnogueira.xpdlparser.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RequirementSpecification")
@XmlAccessorType (XmlAccessType.FIELD)
//@XmlType(propOrder = {"businessRules", "functionalRequirements"})
public class RequirementSpecification {
	
	@XmlElement(name = "Metadata")
	public Metadata metadata = new Metadata();
	
	@XmlElementWrapper(name = "Actors")
	@XmlElement(name = "Actor")
	public List<Actor> actors = new  ArrayList<Actor>();
	
	@XmlElementWrapper(name = "PreConditions")
	@XmlElement(name = "PreCondition")
	public List<PreCondition> preConditions = new  ArrayList<PreCondition>();
	
	@XmlElementWrapper(name = "PostConditions")
	@XmlElement(name = "PostCondition")
	public List<PostCondition> postConditions = new  ArrayList<PostCondition>();
	
	@XmlElementWrapper(name = "FunctionalRequirements")
	@XmlElement(name = "FunctionalRequirement")
	public List<FunctionalRequirement> functionalRequirements = new  ArrayList<FunctionalRequirement>();
	
	@XmlElementWrapper(name = "NonFunctionalRequirements")
	@XmlElement(name = "NonFunctionalRequirement")
	public List<NonFunctionalRequirement> nonFunctionalRequirements = new  ArrayList<NonFunctionalRequirement>();
	
	@XmlElementWrapper(name = "BusinessRules")
	@XmlElement(name = "BusinessRule")
	public List<BusinessRule> businessRules = new  ArrayList<BusinessRule>();
	
	@XmlElement(name = "UseCaseDiagram")
	public UseCaseDiagram useCaseDiagram = new UseCaseDiagram();
	
	@XmlElement(name = "ClassDiagram")
	public ClassDiagram classDiagram = new ClassDiagram();
	
	@XmlElement(name = "ActivityDiagram")
	public ActivityDiagram activityDiagram = new ActivityDiagram();
	
	@XmlElementWrapper(name = "DomainClasses")
	@XmlElement(name = "Class")
	public List<DomainClass> domainClasses = new  ArrayList<DomainClass>();
	
	public List<BusinessRule> getBusinessRules() {
		return businessRules;
	}
	public void setBusinessRules(List<BusinessRule> businessRules) {
		this.businessRules = businessRules;
	}
	public List<FunctionalRequirement> getFunctionalRequirements() {
		return functionalRequirements;
	}
	public void setFunctionalRequirements(
			List<FunctionalRequirement> functionalRequirements) {
		this.functionalRequirements = functionalRequirements;
	}
	public List<NonFunctionalRequirement> getNonFunctionalRequirements() {
		return nonFunctionalRequirements;
	}
	public void setNonFunctionalRequirements(
			List<NonFunctionalRequirement> nonFunctionalRequirements) {
		this.nonFunctionalRequirements = nonFunctionalRequirements;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public List<PreCondition> getPreConditions() {
		return preConditions;
	}
	public void setPreConditions(List<PreCondition> preConditions) {
		this.preConditions = preConditions;
	}
	public List<PostCondition> getPostConditions() {
		return postConditions;
	}
	public void setPostConditions(List<PostCondition> postConditions) {
		this.postConditions = postConditions;
	}
	public UseCaseDiagram getUseCaseDiagram() {
		return useCaseDiagram;
	}
	public void setUseCaseDiagram(UseCaseDiagram useCaseDiagram) {
		this.useCaseDiagram = useCaseDiagram;
	}
	public ClassDiagram getClassDiagram() {
		return classDiagram;
	}
	public void setClassDiagram(ClassDiagram classDiagram) {
		this.classDiagram = classDiagram;
	}
	public List<DomainClass> getDomainClasses() {
		return domainClasses;
	}
	public void setDomainClasses(List<DomainClass> domainClasses) {
		this.domainClasses = domainClasses;
	}
	public ActivityDiagram getActivityDiagram() {
		return activityDiagram;
	}
	public void setActivityDiagram(ActivityDiagram activityDiagram) {
		this.activityDiagram = activityDiagram;
	}
	
	
	

}
