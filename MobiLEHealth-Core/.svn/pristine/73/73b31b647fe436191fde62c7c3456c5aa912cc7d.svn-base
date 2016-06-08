package mobilehealth.prc.analyzer;

import java.util.List;

public interface IAnalyzer {

	/**
	 * Release all the resources related to the semantic process.
	 * This function must be called before the software ending.
	 */
	public void finalize();

	/**
	 * Initializes all the resources and libraries used on the semantic
	 * augmentation process.
	 * @param dir the directory where the domain ontologies are saved.
	 * The library must have write rights on the directory.
	 */
	public void init(String dir);

	/**
	 * Add a new domain ontology on the semantic process. This ontology
	 * will be saved on the directory specified on the init().
	 * @param aOntology an ontology's body. The ontology must be in the RDF/XML
	 * format.
	 */
	public void addDomainOntology(String aOntology);

	/**
	 * Removes the ontology from that contains the specified URI
	 * @param aOntologyURI the URI of the ontology to be removed
	 */
	public void removeDomainOntology(String aOntologyURI);

	/**
	 * Get the domain ontology specified by the aOntologyURI parameter
	 * @param aOntologyURI the URI of the ontology to be returned
	 * @return the ontology's body in  RDF/XML format
	 */
	public String getDomainOntoForURL(String aOntologyURI);

	/**
	 * Return the user profile ontology in RDF/XML format.
	 * @return the user profile ontology's body
	 */
	public String getProfileOnto();

	/**
	 * Domain's ontologies are saved on an MetadataMap where the key value
	 * represents the default namespace of the ontology and the value represents
	 * the body of the ontology
	 * @return the MetadataMap containing the domains ontologies.
	 */
	public MetadataMap getDomainsOntologies();

	/**
	 * Sets a new working directory, the directory must contain the SESProfile ontology and any domains
	 * ontologies to be used. The software must have permission to write on the "dir" directory
	 * to be able to write the both config file and updated ontologies.
	 * @param dir string containing the path to the working directory.
	 */
	public void setWorkingDirectory(String dir);

	/**
	 * return the working directory defined by the setWorkingDiretory function
	 * or by init function.
	 * @return the string containing the working directory path.
	 */
	public String getWorkingDirectory();

	/**
	 * Adds explicitly a new user on the profile ontology with optional metadata to be
	 * saved in the ontology. The metadata object must contain a key named "UserID" with
	 * the value of the user identification.
	 * @param metadata the user's metadata data object
	 * @return the new user's URI created on the ontology
	 */
	public String addUser(MetadataMap metadata);

	/**
	 * Explicitly removes the user identified by his userID from the SESProfile ontology.
	 * All data related to the user will be removed too.
	 * @param userID the user identification code
	 * @return return true if the user was successfully removed, otherwise false
	 */
	public boolean removeUser(String userID);

	/**
	 * update an user's metadata (specified by the userID string), saved on the ontology.
	 * The data to be updated must conform to the named dataproperty.
	 * @param userID the user identifier
	 * @param dataName the name of the ontology's dataproperty to be updated 
	 * @param value the new value of the dataproperty
	 */
	public void updateUserData(String userID, String dataName, String value);

	/**
	 * processes the semantic augmentation on the specified url. The url can point to web sites,
	 * files or any other resource that can be declared on an URL object format. With this function,
	 * the content of the url will be downloaded to SES. 
	 * @param url the url path of the resource to be augmented. The string must conform to the URL format
	 * @param metadata metadata related with the solicitation. The metadata object must contain a entry with
	 * key = "URL", with the value the URL being augmented
	 */
	public void processURLWithMetadata(String url, MetadataMap metadata);

	/**
	 * processes the semantic augmentation on the content param.
	 * @param content the data to be augmented
	 * @param metadata metadata related with the solicitation. The metadata object must contain a entry with
	 * key = "URL", with the value the URL identifier of the content being augmented
	 */
	public void processDataWithMetadata(String content, MetadataMap metadata);

	/**
	 * returns an normalized number 0..1 that represents the user interest on the specified domain.
	 * @param userID the user identifier. The value is obtained by recursive calls in the ontology's tree
	 * where all child resource is used in the calculation. If the intent is obtain the value for all ontology
	 * is enough send a base class URI.
	 * @param domainURI the ontology's domain URI. 
	 * @param recursive define if only the specified domainURI must be used or must be included all the child URIs
	 * @return the user interest normalized
	 */
	public float getUserInterest(String userID, String domainURI, boolean recursive);

	/**
	 * This function calculates the relatioship between an domain resource, specified by domainURI, and 
	 * and an content resource, specified by resourceURI. The value is normalized and are in the 0..1 interval.
	 * 0 represents no relation between and 1, max relation.
	 * @param resourceURI The URI of the resource being verified. This can be a web site, comment, video or
	 * any other resource.
	 * @param domainURI the domain resource being verified. To verify the complete domain ontology 
	 * is enough to send a base URI.
	 * @param recursive define if only the specified domainURI must be used or must be included all the child URIs
	 * @return the relation between the domain and content.
	 */
	public float getResourceDomainRelation(String resourceURL, String domainURI, boolean recursive);

	/**
	 * This was not implemented yet.
	 * @param userID
	 * @return
	 */
	public List<MetadataMap> getUserHistory(int userID);

	/**
	 * Creates and returns a new MetadataMap, an attribute-value matrix that represents meta-data
	 * on a resource, URI, augment request and anything else we feel like. In augment request, the
	 * key value must be a java string conform the fields specified by the profile ontology. 
	 * @return a empty new MetadataMap object
	 */
	public MetadataMap newMetadataMap();
}