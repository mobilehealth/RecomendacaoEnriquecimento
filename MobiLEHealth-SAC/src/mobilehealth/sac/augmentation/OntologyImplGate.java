package mobilehealth.sac.augmentation;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import gate.Factory;
import gate.FeatureMap;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.*;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OConstants.OntologyFormat;
import gate.util.GateException;

public class OntologyImplGate implements mobilehealth.sac.augmentation.Ontology {

	gate.creole.ontology.Ontology onto;

	public OntologyImplGate() {

		try {
			GateUtil.initialize();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (GateException e1) {
			e1.printStackTrace();
		}

		try {
			FeatureMap ontoParams = Factory.newFeatureMap();
			onto = (Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", ontoParams);

		} catch (ResourceInstantiationException e) {
			System.out.println("erro: inicialização da ontologia");
			e.printStackTrace();
		}
	}

	public OntologyImplGate(String ontoPath) {

		try {
			GateUtil.initialize();
		} catch (MalformedURLException | GateException e) {
			System.out.println("erro: inicialização do GATE");
			e.printStackTrace();
		}

		try {
			String urlStr = "File:/" + ontoPath;
			URL ontoURL;
			ontoURL = new URL(urlStr);
			FeatureMap ontoParams = Factory.newFeatureMap();
			ontoParams.put("rdfXmlURL", ontoURL);
			onto = (Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", ontoParams);

		} catch (MalformedURLException | ResourceInstantiationException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#cleanOntology()
	 */
	@Override
	public void cleanOntology() {
		onto.cleanOntology();
	}

	public void finalize() {
		Factory.deleteResource(onto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#writeOntologyData(java.io.Writer,
	 * gate.creole.ontology.OConstants.OntologyFormat, boolean)
	 */
	@Override
	public void writeOntologyData(Writer out, boolean includeExports) {
		onto.writeOntologyData(out, OntologyFormat.RDFXML, includeExports);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#readOntologyData(java.io.Reader, java.lang.String,
	 * boolean)
	 */
	@Override
	public void readOntologyData(Reader in, String baseURI, boolean asImport) {
		onto.readOntologyData(in, baseURI, OntologyFormat.RDFXML, asImport);

		Set<OClass> topClasses = onto.getOClasses(true);
		String ontoNamespace = topClasses.iterator().next().getONodeID().getNameSpace();
		onto.setDefaultNameSpace(ontoNamespace);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getOntologyURI()
	 */
	@Override
	public URI getOntologyURI() {
		try {
			return new URI(onto.getOntologyURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#setOntologyURI(java.net.URI)
	 */
	@Override
	public void setOntologyURI(URI theURI) {
		onto.setOntologyURI(onto.createOURI(theURI.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getURL()
	 */
	@Override
	public URL getURL() {
		return onto.getURL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#setURL(java.net.URL)
	 */
	@Override
	public void setURL(URL aUrl) {
		onto.setURL(aUrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#setDefaultNameSpace(java.lang.String)
	 */
	@Override
	public void setDefaultNameSpace(String aNameSpace) {
		onto.setDefaultNameSpace(aNameSpace);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getDefaultNameSpace()
	 */
	@Override
	public String getDefaultNameSpace() {
		return onto.getDefaultNameSpace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#containsOClass(java.lang.String)
	 */
	@Override
	public boolean containsOClass(String theURI) {
		gate.creole.ontology.OURI onode = onto.createOURI(theURI);
		return onto.containsOClass(onode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getOClasses(boolean)
	 */
	@Override
	public Set<String> getOClasses(boolean top) {

		Set<OClass> classes = onto.getOClasses(top);
		Set<String> classesURI = new HashSet<String>();

		for (OClass oClass : classes) {
			classesURI.add(oClass.getONodeID().toString());
		}

		return classesURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getDistance(java.lang.String, java.lang.String)
	 */
	@Override
	public int getDistance(String class1URI, String class2URI) {
		OClass class1 = onto.getOClass(onto.createOURI(class1URI));
		OClass class2 = onto.getOClass(onto.createOURI(class2URI));

		return onto.getDistance(class1, class2);
	}

	public List<String> getObjectPropertyValuesForInstance(String aPropertyURI, String aInstaceURI) {

		// Cria a lista de retorno
		List<OInstance> listInsts;
		List<String> listInstURIs = new ArrayList<String>();

		// Verifica se a instância existe
		OInstance inst = onto.getOInstance(onto.createOURI(aInstaceURI));
		ObjectProperty objProp = onto.getObjectProperty(onto.createOURI(aPropertyURI));

		if (inst != null && objProp != null) {
			if (objProp.isValidDomain(inst)) {
				listInsts = inst.getObjectPropertyValues(objProp);

				for (OInstance oInstance : listInsts) {
					listInstURIs.add(oInstance.getOURI().toString());
				}
			}
		}

		return listInstURIs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#addOInstance(java.lang.String, java.lang.String)
	 */
	@Override
	public String addOInstance(String theResourcePrefix, String theClassURI) {

		OURI theInstanceURI = onto.generateOURI(theResourcePrefix);
		OClass theClass = onto.getOClass(onto.createOURI(theClassURI));

		OInstance inst = onto.addOInstance(theInstanceURI, theClass);
		return inst.getOURI().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#removeOInstance(java.lang.String)
	 */
	@Override
	public void removeOInstance(String theInstance) {
		OInstance inst = onto.getOInstance(onto.createOURI(theInstance));
		onto.removeOInstance(inst);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getOInstances()
	 */
	@Override
	public Set<String> getOInstances() {

		Set<OInstance> insts = onto.getOInstances();
		Set<String> instsURI = new HashSet<String>();

		for (OInstance oinst : insts) {
			instsURI.add(oinst.getOURI().toString());
		}

		return instsURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#createInstaceForClass(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String createInstaceForClass(String instanceName, String classURI) {

		OURI instOURI = onto.createOURIForName(instanceName);
		OClass theClass = onto.getOClass(onto.createOURI(classURI));

		OInstance inst = onto.addOInstance(instOURI, theClass);

		return inst.getOURI().toString();
	}

	public void updateDataPropertyValue(String instURI, String dtURI, String value) {

		OInstance inst = onto.getOInstance(onto.createOURI(instURI));
		DatatypeProperty dp = onto.getDatatypeProperty(onto.createOURI(dtURI));
		List<Literal> dpValues = inst.getDatatypePropertyValues(dp);

		// Como só deve ter um dataproperty "setado" para cada tipo de dado não precisamos
		// verifica mais de um item
		if (!dpValues.isEmpty())
			inst.removeDatatypePropertyValues(dp);

		try {
			inst.addDatatypePropertyValue(dp, new Literal(value));
		} catch (InvalidValueException e) {
			e.printStackTrace();
		}
	}

	public String getDatatypePropertyValue(String instURI, String dpURI) {

		OInstance inst = onto.getOInstance(onto.createOURI(instURI));
		List<Literal> values = inst.getDatatypePropertyValues(onto.getDatatypeProperty(onto.createOURI(dpURI)));

		if (values.isEmpty())
			return null;
		else
			return values.get(0).toString();
	}

	public void setLabelForInstance(String instURI, String labelText) {

		OInstance inst = onto.getOInstance(onto.createOURI(instURI));
		inst.setLabel(labelText, OConstants.ENGLISH);
	}

	public void removeLabelValueForInstance(String value, String instURI) {

		OInstance inst = onto.getOInstance(onto.createOURI(instURI));
		AnnotationProperty ann = onto.getAnnotationProperty(onto.createOURI("http://www.w3.org/2000/01/rdf-schema#label"));
		inst.removeAnnotationPropertyValue(ann, new Literal(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ubi.analytic.SemanticIndexer.Ontology#setDataPropertyValueForInstanceClass(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void setDataPropertyValueForInstance(String newValue, String oldValue, String dpURI, String instURI) {

		DatatypeProperty dp = onto.getDatatypeProperty(onto.createOURI(dpURI));
		OInstance inst = onto.getOInstance(onto.createOURI(instURI));

		boolean result = dp.isValidDomain(inst);

		if (result) {
			inst.removeDatatypePropertyValue(dp, new Literal(oldValue));

			try {
				inst.addDatatypePropertyValue(dp, new Literal(newValue));
			} catch (InvalidValueException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ubi.analytic.SemanticIndexer.Ontology#setDataPropertyValueForInstanceClass(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String getDatatypePropertyValueForInstance(String dpName, String instURI) {

		DatatypeProperty dp = onto.getDatatypeProperty(onto.createOURI(onto.getDefaultNameSpace() + dpName));
		OInstance inst = onto.getOInstance(onto.createOURI(instURI));

		if (dp.isValidDomain(inst)) {

			List<Literal> values = inst.getDatatypePropertyValues(dp);
			if (values.size() != 0)
				return values.get(0).toString();
		}

		return null;
	}

	public void addObjectPropertyValueForInst(String objPrURI, String valueURI, String instURI) {

		OInstance inst = onto.getOInstance(onto.createOURI(instURI));
		OInstance objInst = onto.getOInstance(onto.createOURI(valueURI));
		ObjectProperty objP = onto.getObjectProperty(onto.createOURI(objPrURI));

		try {
			inst.addObjectPropertyValue(objP, objInst);
		} catch (InvalidValueException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#setAnnotationPropertyForClass(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void setAnnotationPropertyForClass(String annotURI, String classURI, String value) {

		AnnotationProperty ann = onto.getAnnotationProperty(onto.createOURI(annotURI));
		OClass oclass = onto.getOClass(onto.createOURI(classURI));

		oclass.addAnnotationPropertyValue(ann, new Literal(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getOInstances(java.lang.String, boolean)
	 */
	@Override
	public Set<String> getOInstances(String theClass, boolean closure) {

		Set<String> instancesURI = new HashSet<>();
		OClass oclass = onto.getOClass(onto.createOURI(theClass));
		Closure constant = (closure) ? OConstants.Closure.DIRECT_CLOSURE : OConstants.Closure.TRANSITIVE_CLOSURE;

		Set<OInstance> oInst = onto.getOInstances(oclass, constant);

		for (OInstance oInstance : oInst) {
			instancesURI.add(oInstance.getOURI().toString());
		}

		return instancesURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#containsOInstance(java.lang.String)
	 */
	@Override
	public boolean containsOInstance(String theInstanceURI) {

		return onto.containsOInstance(onto.createOURI(theInstanceURI));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#isRDFProperty(java.lang.String)
	 */
	@Override
	public boolean isRDFProperty(String thePropertyURI) {
		return onto.isRDFProperty(onto.createOURI(thePropertyURI));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#isAnnotationProperty(java.lang.String)
	 */
	@Override
	public boolean isAnnotationProperty(String thePropertyURI) {
		return onto.isAnnotationProperty(onto.createOURI(thePropertyURI));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#isDatatypeProperty(java.lang.String)
	 */
	@Override
	public boolean isDatatypeProperty(String thePropertyURI) {
		return onto.isDatatypeProperty(onto.createOURI(thePropertyURI));
	}

	// *****************************
	// Ontology Modification Events
	// *****************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#setModified(boolean)
	 */
	@Override
	public void setModified(boolean isModified) {
		onto.setModified(isModified);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#isModified()
	 */
	@Override
	public boolean isModified() {
		return onto.isModified();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#getOResourcesByName(java.lang.String)
	 */
	@Override
	public List<String> getOResourcesByName(String resourceName) {
		List<OResource> resources = onto.getOResourcesByName(resourceName);

		List<String> resourcesURI = new ArrayList<>();

		for (OResource oResource : resources) {
			resourcesURI.add(oResource.getONodeID().toString());
		}

		return resourcesURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubi.analytic.SemanticIndexer.Ontology#createORIForName(java.lang.String)
	 */
	@Override
	public URI createORIForName(String resourceName) {
		try {
			return new URI(onto.createOURI(resourceName).toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void readOntologyData(String ontologyData) {

		StringReader ontoText = new StringReader(ontologyData);
		onto.cleanOntology();
		onto.readOntologyData(ontoText, null, OConstants.OntologyFormat.RDFXML, false);

		Set<OClass> topClasses = onto.getOClasses(true);

		String ontoNamespace = topClasses.iterator().next().getONodeID().getNameSpace();
		onto.setDefaultNameSpace(ontoNamespace);
	}

	public List<String> findResourceInstancesWithURL(String classURI, String value) {

		List<String> result = new ArrayList<>();

		OClass theClass = onto.getOClass(onto.createOURI(classURI));
		Set<OInstance> insts = onto.getOInstances(theClass, Closure.DIRECT_CLOSURE);

		for (OInstance instance : insts) {
			Set<Literal> labels = instance.getLabels();
			for (Literal literal : labels) {
				if (literal.toString().equals(value))
					result.add(instance.getOURI().toString());
			}
		}

		return result;
	}

	public String getResourceForTypeURIandValue(resourceType rType, String uri, String value) {

		Set<OInstance> insts = null;
		OClass theClass = null;

		switch (rType) {
		case RESOURCE:
			theClass = onto.getOClass(onto.createOURI(onto.getDefaultNameSpace() + "Resource"));
			insts = onto.getOInstances(theClass, OConstants.Closure.DIRECT_CLOSURE);
			for (OInstance instance : insts) {
				Set<Literal> labels = instance.getLabels();

				for (Literal label : labels) {
					if (label.toString().equals(value))
						return instance.getOURI().toString();
				}
			}
			break;

		case LINK:
			theClass = onto.getOClass(onto.createOURI(onto.getDefaultNameSpace() + "Links"));
			insts = onto.getOInstances(theClass, OConstants.Closure.DIRECT_CLOSURE);
			for (OInstance instance : insts) {
				Set<Literal> labels = instance.getLabels();

				DatatypeProperty aProperty = onto.getDatatypeProperty(onto.createOURI(onto.getDefaultNameSpace() + "URI"));

				for (Literal label : labels) {
					if (label.toString().equals(value))
						if (instance.hasDatatypePropertyWithValue(aProperty, new Literal(uri)))
							return instance.getOURI().toString();
				}
			}
			break;

		case ACCESS:
			theClass = onto.getOClass(onto.createOURI(onto.getDefaultNameSpace() + "Access"));
			break;

		case USER:
			theClass = onto.getOClass(onto.createOURI(onto.getDefaultNameSpace() + "User"));
			break;
		}

		return null;
	}
}
