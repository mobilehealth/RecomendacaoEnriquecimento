package mobilehealth.sac.augmentation;

import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

public interface Ontology {

	public enum resourceType {
		RESOURCE,
		LINK,
		ACCESS,
		USER
	}
	
	/**
	 * This method removes the entire data from the ontology and emptys
	 * it. This will also re-initialize the ontology to the state it would have
	 * after creation and perform the import of system data into the ontology
	 * store (OWL and RDFS assertions).
	 */
	public abstract void cleanOntology();

	public void finalize(); 
	
	/**
	 * Write the ontology data to the provided writer in the specified 
	 * serialization format. The writer object has to be closed by the caller.
	 * 
	 * @param out an open Writer for writing the data
	 * @param format the ontology serialization format , see
	 * {@link OConstants.OntologyFormat}
	 * @param includeExports if false, do not write any data that was loaded as
	 * and ontology import.
	 */
	public abstract void writeOntologyData(Writer out, boolean includeExports);

	/**
	 * Read ontology data from the specified reader in the specified format
	 * and load it into the ontology.
	 * @param in
	 * @param baseURI 
	 * @param format
	 * @param asImport asImport if true, load the data as an ontology import which means
	 *   that it will not be written as part of the user data, unless explicitly
	 *   requested.
	 */
	public abstract void readOntologyData(Reader in, String baseURI,
			boolean asImport);

	/**
	 * Get the URI of this ontology. If no ontology URI is found, null is
	 * returned. If more than one ontology URI is found, an exception is
	 * thrown.
	 * 
	 * @return the OURI of the current ontology or null
	 */
	public abstract URI getOntologyURI();

	/**
	 * Set the ontology URI of the current ontology. If the ontology
	 * URI is already set to a different value, this method throws an exception.
	 * <p>
	 * NOTE: this method does not set the default namespace!
	 * <p>
	 * NOTE: at the moment, this method allows to set the ontology URI as long as no
	 * URI is set yet. Once an ontology URI is set, it cannot be changed
	 * since it would not be clear what to do with assertions that alreadt reference
	 * that ontology URI (e.g. ontology annotations or import specifications).
	 *
	 * @param theURI
	 */
	public abstract void setOntologyURI(URI theURI);

	/**
	 * Gets the URL of this ontology. This usually is the URL the ontology was
	 * loaded  from. If several files were loaded, the URL of the first file
	 * is returned. Files loaded as imports are not considered for this.
	 * If and how this is set automatically when an ontology LR
	 * is created depends on the implementation.
	 * For an ontology LR that connects to an existing ontology repository,
	 * an URL derived from the ontology repository location may be returned.
	 * 
	 * @return the URL of this ontology if set, or null otherwise
	 */
	public abstract URL getURL();

	/**
	 * Set the URL of this ontology.
	 * This URL set by this method will be returned by the {@link #getURL()}
	 * method. The ontology store is not modified by this.
	 * 
	 * @param aUrl the url to be set
	 */
	public abstract void setURL(URL aUrl);

	/**
	 * Sets the default name space/base URI for the ontology.
	 * This URI must end in "#" or "/". This URI is used when a new OURI
	 * is created using the {@link #createOURIForName(String)} method.
	 * Setting the default name space with this method does not change the
	 * ontology store and does not add a default namespace declaration to the
	 * store or when the ontology is saved.
	 * 
	 * @param aNameSpace a String that can be used as a base URI
	 */
	public abstract void setDefaultNameSpace(String aNameSpace);

	/**
	 * Gets the default name space for this ontology.
	 * This is used as the base URI for the ontology.
	 * This returns the last value set with the method setDefaultNameSpace.
	 * If the default name space was not set with this method, it is set
	 * to a default value when an ontology is loaded in the following way:
	 * If a base URI is specified for the loading, that base URI is used,
	 * otherwise, if there was no ontology URI already set from a previous
	 * load and this load determined exactly one ontology URI, that URI
	 * will be used to set the default name space.
	 * 
	 * @return a String value.
	 */
	public abstract String getDefaultNameSpace();

	/**
	 * Checks whether a class with the specified URI or blank node ID
	 * exists in the ontology.
	 * 
	 * @param theURI a ONodeID, usually an OURI specifying the ID of the
	 * ontology class
	 * @return true, if the class exists 
	 */
	public abstract boolean containsOClass(String theURI);

	/**
	 * Retrieves all ontology classes in a set.
	 * This method returns a set of either all classes in the ontology or
	 * just the "top" classes. A "top" class is a class that is not a subclass
	 * of any other class that is not a predefined system class like owl:Thing
	 * or rdfs:Resource or a trivial subclass (of itself or of a class that
	 * is defined to be equivalent to itself).
	 * <p>
	 * NOTE: for large ontologies with a large number of classes it may be
	 * preferable to use method {@link #getOClassesIterator(boolean)} instead.
	 * 
	 * @param top If set to true, only returns those classes with no super
	 *          classes, otherwise - a set of all classes.
	 * @return set of all the classes in this ontology
	 */
	public abstract Set<String> getOClasses(boolean top);

	/**
	 * Gets the taxonomic distance between 2 classes.
	 * 
	 * @param class1 the first class
	 * @param class2 the second class
	 * @return the taxonomic distance between the 2 classes
	 */
	public abstract int getDistance(String class1URI, String class2URI);

	/**
	 * Creates a new OInstance and returns it.
	 * 
	 * @param theResourcePrefix 
	 * @param theClass the class to which the instance belongs.
	 * @return the OInstance that has been added to the ontology.
	 */
	public abstract String addOInstance(String theResourcePrefix,
			String theClassURI);

	/**
	 * Removes the instance from the ontology.
	 * 
	 * @param theInstance to be removed
	 */
	public abstract void removeOInstance(String theInstance);

	/**
	 * Gets all instances in the ontology.
	 * 
	 * @return a {@link Set} of OInstance objects
	 */
	public abstract Set<String> getOInstances();

	public abstract String createInstaceForClass(String instanceName,
			String classURI);

	public abstract void setDataPropertyValueForInstance (
			String newValue, String oldValue, String dpURI, String instURI);
	
	public String getDatatypePropertyValueForInstance (String dpName, String instURI);

	public abstract void setAnnotationPropertyForClass(String annotURI,
			String classURI, String value);

	public void setLabelForInstance(String instURI, String labelText);
	
	public void removeLabelValueForInstance (String value, String instURI);
	
	public List<String> findResourceInstancesWithURL(String classURI, String value);
	
	public void updateDataPropertyValue(String instURI, String dtURI, String value);
	
	public String getDatatypePropertyValue(String instURI, String dpURI);
	
	public List<String> getObjectPropertyValuesForInstance(String aPropertyURI, String aInstaceURI);
	
	public void addObjectPropertyValueForInst(String objPrURI, String valueURI, String instURI);
	
	/**
	 * Gets instances in the ontology, which belong to this class.
	 * The second parameter specifies if the the given class needs to be
	 * a direct class of the instance (direct closure)
	 * or a class to which the instance belongs
	 * indirectly (transitive closure)
	 *
	 * @param theClass the class of the instances
	 * @param closure either {@link OConstants.Closure#DIRECT_CLOSURE} or
	 * {@link OConstants.Closure#TRANSITIVE_CLOSURE}
	 *
	 * @return {@link Set} of OInstance objects
	 */
	public abstract Set<String> getOInstances(String theClass, boolean closure);

	/**
	 * Checks whether the provided Instance exists in the ontology.
	 * 
	 * @param theInstance
	 * @return true, if the Instance exists in ontology, otherwise -
	 *         false.
	 */
	public abstract boolean containsOInstance(String theInstanceURI);

	/**
	 * Checkes whether there exists a statement <thePropertyURI, RDF:Type,
	 * RDF:Property> in the ontology or not.
	 * 
	 * @param thePropertyURI
	 * @return true, only if there exists the above statement, otherwise -
	 *         false.
	 */
	public abstract boolean isRDFProperty(String thePropertyURI);

	/**
	 * Checkes whether there exists a statement <thePropertyURI, RDF:Type,
	 * OWL:AnnotationProperty> in the ontology or not.
	 * 
	 * @param thePropertyURI
	 * @return true, only if there exists the above statement, otherwise -
	 *         false.
	 */
	public abstract boolean isAnnotationProperty(String thePropertyURI);

	/**
	 * Checkes whether the ontology contains a datatype property with the
	 * given URI.
	 * 
	 * @param thePropertyURI
	 * @return true if there is an instance of owl:DatatypeProperty with the
	 * given URI in the ontology.
	 */
	public abstract boolean isDatatypeProperty(String thePropertyURI);

	// *****************************
	// Ontology Modification Events
	// *****************************
	/**
	 * Sets the modified flag.
	 * 
	 * @param isModified sets this param as a value of the modified
	 *          property of the ontology
	 */
	public abstract void setModified(boolean isModified);

	/**
	 * Checks the modified flag.
	 * 
	 * @return whether the ontology has been modified after the loading
	 */
	public abstract boolean isModified();

	/**
	 * This method checks in its cache to find out the OResources for the
	 * given resource name. It is possible for two resources to have a
	 * same name but different name spaces. This method returns a list of
	 * resources with the common name. Please note that deleting an
	 * instance from this list (e.g. list.remove(int/Object)) does not
	 * delete the resource from an ontology. One must use appropriate
	 * method from the Ontology interface to delete such resources.
	 */
	public abstract List<String> getOResourcesByName(String resourceName);

	/**
	 * Create an OURI from the given resource name, using the ontology base URI
	 * (default name space). This method will throw an exception if no
	 * default name space is set (i.e. if the method getDefaultNameSpace would
	 * return null).
	 *
	 * @param resourceName the resource name i.e. the part of the final URI/IRI
	 * that is attached to the base URI (aftaer a trailing "#" or "/").
	 * @return the OURI 
	 */
	public abstract URI createORIForName(String resourceName);
	
	public void readOntologyData(String ontologyData);
	
	public String getResourceForTypeURIandValue (resourceType rType, String uri,String value);

}