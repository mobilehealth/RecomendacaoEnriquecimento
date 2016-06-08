package mobilehealth.prc.analyzer;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import mobilehealth.prc.analyzer.semantic.augmentation.SemanticAugmentation;
import mobilehealth.prc.analyzer.semantic.augmentation.SemanticAugmentationImplGate;
import mobilehealth.prc.analyzer.semantic.indexer.SemanticIndexer;
import mobilehealth.prc.analyzer.semantic.indexer.SemanticIndexerImpl;

public class Analyzer implements IAnalyzer
{
	private SemanticIndexer indexer;
	private SemanticAugmentation semantic;
	
	public Analyzer() {
		
	}

	/* (non-Javadoc)
	 * @see ubi.analytic.SES#finalize()
	 */
	@Override
	public void finalize() {

		indexer.finalize();
		semantic.finalize();
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#init(java.lang.String)
	 */
	@Override
	public void init(String dir) {
		
		indexer = new SemanticIndexerImpl(dir);
		semantic = new SemanticAugmentationImplGate(indexer);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#addDomainOntology(java.lang.String)
	 */
	@Override
	public void addDomainOntology(String aOntology) {

		indexer.addDomainOntology(aOntology);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#removeDomainOntology(java.lang.String)
	 */
	@Override
	public void removeDomainOntology(String aOntologyURI) {
		
		indexer.removeDomainOntology(aOntologyURI);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getDomainOntoForURL(java.lang.String)
	 */
	@Override
	public String getDomainOntoForURL(String aOntologyURI) {

		return indexer.getDomainOntoForURL(aOntologyURI);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getProfileOnto()
	 */
	@Override
	public String getProfileOnto() {
		
		return indexer.getProfileOnto();
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getDomainsOntologies()
	 */
	@Override
	public MetadataMap getDomainsOntologies() {
		
		return indexer.getDomainsOntologies();
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#setWorkingDirectory(java.lang.String)
	 */
	@Override
	public void setWorkingDirectory (String dir) {

		indexer.setWorkDir(dir);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getWorkingDirectory()
	 */
	@Override
	public String getWorkingDirectory() {
		return indexer.getWorkDir();
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#addUser(ubi.analytic.MetadataMap)
	 */
	@Override
	public String addUser(MetadataMap metadata) {

		return indexer.addUser(metadata);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#removeUser(java.lang.String)
	 */
	@Override
	public boolean removeUser(String userID) {
		
		return indexer.removeUser(userID);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#updateUserData(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateUserData(String userID, String dataName, String value) {
		
		indexer.updateUserData(userID, dataName, value);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#processURLWithMetadata(java.net.URL, ubi.analytic.MetadataMap)
	 */
	@Override
	public void processURLWithMetadata(String url, MetadataMap metadata) {
		
		URL urlOk = null;
		try {
			urlOk = new URL(url);
			semantic.processURLWithMetadata(urlOk, metadata);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#processDataWithMetadata(java.lang.String, ubi.analytic.MetadataMap)
	 */
	@Override
	public void processDataWithMetadata(String content, MetadataMap metadata) {

		semantic.processDataWithMetadata(content, metadata);
	}
	
	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getUserInterest(int, java.net.URL)
	 */
	@Override
	public float getUserInterest(String userID, String domainURI, boolean recursive) {
		return indexer.getUserInterest(userID, domainURI, recursive);
	}

	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getResourceDomainRelation(java.net.URL, java.net.URL)
	 */
	@Override
	public float getResourceDomainRelation(String resourceURL, String domainURI, boolean recursive) {
		return indexer.getResourceDomainRelation(resourceURL, domainURI, recursive);
	}

	/* (non-Javadoc)
	 * @see ubi.analytic.SES#getUserHistory(int)
	 */
	@Override
	public List<MetadataMap> getUserHistory(int userID) {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see ubi.analytic.SES#newMetadataMap()
	 */
	@Override
	public MetadataMap newMetadataMap() {
		return new MetadataMap();
	}
}
